package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.CartService_24110179;
import vn.edu.ktqt.model.CartItem_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CartController_24110179 extends HttpServlet {
    private final CartService_24110179 cartService = new CartService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, CartItem_24110179> cart = cartService.getCart(request.getSession());
        request.setAttribute("cartItems", cart.values());
        request.setAttribute("total", cartService.total(cart.values()));
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int bookId = parseInt(request.getParameter("bookId"), 0);
        int quantity = parseInt(request.getParameter("quantity"), 1);
        try {
            if ("remove".equals(action)) cartService.remove(request.getSession(), bookId);
            else if ("update".equals(action)) cartService.update(request.getSession(), bookId, quantity);
            else cartService.add(request.getSession(), bookId, quantity);
            response.sendRedirect(request.getContextPath() + ("add".equals(action) ? "/cart" : "/cart"));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private int parseInt(String value, int defaultValue) {
        try { return Integer.parseInt(value); } catch (Exception e) { return defaultValue; }
    }
}
