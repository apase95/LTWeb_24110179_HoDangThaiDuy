<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<h1>Đăng ký</h1>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/register" class="auth-form">
    <label>Email</label>
    <input type="email" name="email" placeholder="user@bookstore.com" required>

    <label>Họ tên</label>
    <input type="text" name="fullname" required>

    <label>Số điện thoại</label>
    <input type="number" name="phone">

    <label>Mật khẩu</label>
    <input type="password" name="password" maxlength="32" required>

    <button type="submit">Gửi OTP</button>
</form>
</body>
</html>
