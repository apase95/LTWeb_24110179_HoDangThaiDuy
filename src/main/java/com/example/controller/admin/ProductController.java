package com.example.controller.admin;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.ICategoryService;
import com.example.service.IProductService;
import com.example.service.CategoryServiceImpl;
import com.example.service.ProductServiceImpl;
import com.example.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/products",
        "/admin/product/add",
        "/admin/product/insert",
        "/admin/product/edit",
        "/admin/product/update",
        "/admin/product/delete"
})
@MultipartConfig
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductServiceImpl();
    private ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/products")) {
            // List products
            List<Product> list = productService.findAll();
            req.setAttribute("listproduct", list);
            req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
        } else if (uri.contains("/admin/product/add")) {
            // Show add form with category list
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);
        } else if (uri.contains("/admin/product/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.findById(id);
            List<Category> categories = categoryService.findAll();
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);
        } else if (uri.contains("/admin/product/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/product/insert")) {
            insertProduct(req, resp);
        } else if (uri.contains("/admin/product/update")) {
            updateProduct(req, resp);
        }
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category not found");
            return;
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setStatus(status);
        product.setCategory(category);
        product.setCreatedDate(new Date());

        Part filePart = req.getPart("images");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int idx = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(idx + 1);
            fileName = System.currentTimeMillis() + "." + ext;
            String uploadPath = Constant.UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + fileName);
            product.setImages(fileName);
        } else {
            product.setImages("default.png");
        }

        productService.insert(product);
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Product product = productService.findById(productId);
        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category not found");
            return;
        }

        String oldFile = product.getImages();
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setStatus(status);
        product.setCategory(category);

        Part filePart = req.getPart("images");
        if (filePart != null && filePart.getSize() > 0) {
            if (oldFile != null && !oldFile.startsWith("http")) {
                File old = new File(Constant.UPLOAD_DIR + File.separator + oldFile);
                if (old.exists()) old.delete();
            }
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int idx = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(idx + 1);
            String newFile = System.currentTimeMillis() + "." + ext;
            String uploadPath = Constant.UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + newFile);
            product.setImages(newFile);
        } else {
            product.setImages(oldFile);
        }

        productService.update(product);
        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }
}