package vn.edu.ktqt.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order_24110179 {
    private final int orderId;
    private final String customerName;
    private final String customerPhone;
    private final String customerAddress;
    private final String paymentMethod;
    private final BigDecimal totalAmount;
    private final String status;
    private final Timestamp createdAt;

    public Order_24110179(int orderId, String customerName, String customerPhone, String customerAddress,
                          String paymentMethod, BigDecimal totalAmount, String status, Timestamp createdAt) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public String getCustomerAddress() { return customerAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public Timestamp getCreatedAt() { return createdAt; }
}
