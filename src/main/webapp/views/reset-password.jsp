<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Đặt lại mật khẩu</title></head>
<body>
<h2>Đặt lại mật khẩu</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>
<form action="${pageContext.request.contextPath}/reset-password" method="post">
    <label>Mã OTP đã gửi:</label><br>
    <input type="text" name="otp" required><br><br>
    <label>Mật khẩu mới:</label><br>
    <input type="password" name="newPassword" required><br><br>
    <label>Xác nhận mật khẩu:</label><br>
    <input type="password" name="confirmPassword" required><br><br>
    <input type="submit" value="Đặt lại mật khẩu">
</form>
</body>
</html>