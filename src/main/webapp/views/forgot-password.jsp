<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Quên mật khẩu</title></head>
<body>
<h2>Quên mật khẩu</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>
<form action="${pageContext.request.contextPath}/forgot-password" method="post">
    <label>Nhập email đã đăng ký:</label><br>
    <input type="email" name="email" required><br><br>
    <input type="submit" value="Gửi OTP">
</form>
<p><a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a></p>
</body>
</html>