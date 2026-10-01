package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.CheckoutService_24110179;
import vn.edu.ktqt.model.User_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryController_24110179 extends HttpServlet {
    private final CheckoutService_24110179 checkoutService = new CheckoutService_24110179();
    private final List<String> statuses = Arrays.asList("PENDING", "CONFIRMED", "PREPARING", "SHIPPING", "DELIVERING", "DELIVERED", "CANCELLED", "RETURNED");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User_24110179 user = (User_24110179) request.getSession().getAttribute("currentUser");
        String status = request.getParameter("status");
        if (status != null && !status.isBlank() && !statuses.contains(status)) status = null;
        try {
            request.setAttribute("orders", checkoutService.getOrders(user == null ? null : user.getId(), status));
            request.setAttribute("statuses", statuses);
            request.setAttribute("selectedStatus", status);
            request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
