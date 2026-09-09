<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${pageContext.request.contextPath}/verify-otp" method="post">
    <label>Nhập mã OTP đã gửi đến email:</label>
    <input type="text" name="otp" required>
    <input type="submit" value="Xác nhận">
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>