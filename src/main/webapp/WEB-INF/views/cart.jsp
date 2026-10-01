<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html><head><title>Giỏ hàng</title></head><body>
<h1>Giỏ hàng</h1>
<c:choose>
    <c:when test="${empty cartItems}"><p>Giỏ hàng trống.</p></c:when>
    <c:otherwise>
        <table class="admin-table">
            <tr><th>Sách</th><th>Giá</th><th>Số lượng</th><th>Tồn kho</th><th>Tổng</th><th></th></tr>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>${item.book.title}</td>
                    <td>${item.book.price}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart" class="inline-form">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="bookId" value="${item.book.bookId}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.book.quantity}">
                            <button>Cập nhật</button>
                        </form>
                    </td>
                    <td>${item.book.quantity}</td>
                    <td>${item.subTotal}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart" class="inline-form">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="bookId" value="${item.book.bookId}">
                            <button>Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>Tổng tiền: ${total}</h2>
        <p><a class="button" href="${pageContext.request.contextPath}/checkout">Thanh toán COD</a></p>
    </c:otherwise>
</c:choose>
</body></html>
