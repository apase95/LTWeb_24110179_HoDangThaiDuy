package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.CartService_24110179;
import vn.edu.ktqt.business.CheckoutService_24110179;
import vn.edu.ktqt.model.CartItem_24110179;
import vn.edu.ktqt.model.User_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CheckoutController_24110179 extends HttpServlet {
    private final CartService_24110179 cartService = new CartService_24110179();
    private final CheckoutService_24110179 checkoutService = new CheckoutService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, CartItem_24110179> cart = cartService.getCart(request.getSession());
        request.setAttribute("cartItems", cart.values());
        request.setAttribute("total", cartService.total(cart.values()));
        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<Integer, CartItem_24110179> cart = cartService.getCart(request.getSession());
        User_24110179 user = (User_24110179) request.getSession().getAttribute("currentUser");
        try {
            int orderId = checkoutService.checkoutCod(
                    user == null ? null : user.getId(),
                    request.getParameter("customerName"),
                    request.getParameter("customerPhone"),
                    request.getParameter("customerAddress"),
                    cartService.total(cart.values()),
                    cart.values()
            );
            cart.clear();
            request.setAttribute("orderId", orderId);
            request.getRequestDispatcher("/WEB-INF/views/checkout-success.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}
