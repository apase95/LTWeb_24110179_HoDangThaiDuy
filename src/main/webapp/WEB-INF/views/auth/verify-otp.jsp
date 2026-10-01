<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <title>Xác thực OTP</title>
</head>
<body>
<h1>Xác thực OTP</h1>
<p>Mã OTP đã được gửi đến email: ${sessionScope.registerEmail}</p>
<p class="error">${error}</p>
<form method="post" action="${pageContext.request.contextPath}/verify-otp" class="auth-form">
    <label>OTP</label>
    <input type="text" name="otp" maxlength="6" required>
    <button type="submit">Xác nhận</button>
</form>
</body>
</html>
