<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html><head><title>Thanh toán COD</title></head><body>
<h1>Thanh toán COD</h1>
<p class="error">${error}</p>
<p>Tổng tiền: ${total}</p>
<form method="post" action="${pageContext.request.contextPath}/checkout" class="auth-form">
    <label>Họ tên</label><input name="customerName" value="${sessionScope.currentUser.fullname}" required>
    <label>Số điện thoại</label><input name="customerPhone" required>
    <label>Địa chỉ nhận hàng</label><input name="customerAddress" required>
    <p>Phương thức: COD</p>
    <button type="submit">Đặt hàng</button>
</form>
</body></html>
