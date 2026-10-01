<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<h1>Đăng nhập</h1>
<c:if test="${param.registered == '1'}">
    <p class="success">Đăng ký thành công. Bạn có thể đăng nhập.</p>
</c:if>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/login" class="auth-form">
    <label>Email</label>
    <input type="email" name="email" placeholder="user@bookstore.com" required>

    <label>Mật khẩu</label>
    <input type="password" name="password" required>

    <button type="submit">Đăng nhập</button>
</form>
<p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
</body>
</html>
