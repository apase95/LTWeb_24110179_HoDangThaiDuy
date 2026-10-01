<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html><head><title>Lịch sử đặt hàng</title></head><body>
<h1>Lịch sử đặt hàng</h1>
<form method="get" action="${pageContext.request.contextPath}/orders" class="filter-form">
    <label>Trạng thái</label>
    <select name="status">
        <option value="">Tất cả</option>
        <c:forEach var="status" items="${statuses}">
            <option value="${status}" ${status == selectedStatus ? 'selected' : ''}>
                <c:choose>
                    <c:when test="${status == 'PENDING'}">Đơn hàng mới</c:when>
                    <c:when test="${status == 'CONFIRMED'}">Đã xác nhận</c:when>
                    <c:when test="${status == 'PREPARING'}">Chuẩn bị hàng</c:when>
                    <c:when test="${status == 'SHIPPING'}">Vận chuyển</c:when>
                    <c:when test="${status == 'DELIVERING'}">Giao hàng</c:when>
                    <c:when test="${status == 'DELIVERED'}">Đã giao</c:when>
                    <c:when test="${status == 'CANCELLED'}">Đơn hàng hủy</c:when>
                    <c:otherwise>Đơn hàng hoàn</c:otherwise>
                </c:choose>
            </option>
        </c:forEach>
    </select>
    <button>Lọc</button>
</form>

<table class="admin-table">
    <tr><th>Mã đơn</th><th>Khách hàng</th><th>SĐT</th><th>Địa chỉ</th><th>Thanh toán</th><th>Tổng tiền</th><th>Trạng thái</th><th>Ngày đặt</th></tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>#${order.orderId}</td>
            <td>${order.customerName}</td>
            <td>${order.customerPhone}</td>
            <td>${order.customerAddress}</td>
            <td>${order.paymentMethod}</td>
            <td>${order.totalAmount}</td>
            <td>
                <c:choose>
                    <c:when test="${order.status == 'PENDING'}">Đơn hàng mới</c:when>
                    <c:when test="${order.status == 'CONFIRMED'}">Đã xác nhận</c:when>
                    <c:when test="${order.status == 'PREPARING'}">Chuẩn bị hàng</c:when>
                    <c:when test="${order.status == 'SHIPPING'}">Vận chuyển</c:when>
                    <c:when test="${order.status == 'DELIVERING'}">Giao hàng</c:when>
                    <c:when test="${order.status == 'DELIVERED'}">Đã giao</c:when>
                    <c:when test="${order.status == 'CANCELLED'}">Đơn hàng hủy</c:when>
                    <c:otherwise>Đơn hàng hoàn</c:otherwise>
                </c:choose>
            </td>
            <td><fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm" /></td>
        </tr>
    </c:forEach>
</table>
</body></html>
