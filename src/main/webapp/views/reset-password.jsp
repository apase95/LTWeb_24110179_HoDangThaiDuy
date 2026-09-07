<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Đặt lại mật khẩu</title>
<script>
function validateResetPasswordForm() {
    const otp = document.getElementById('otp').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if (!validateRequired(otp)) {
        showError('otpError', 'OTP không được để trống');
        return false;
    } else {
        clearError('otpError');
    }

    if (!validateRequired(newPassword)) {
        showError('newPasswordError', 'Mật khẩu mới không được để trống');
        return false;
    } else if (!validatePassword(newPassword)) {
        showError('newPasswordError', 'Mật khẩu ít nhất 6 ký tự');
        return false;
    } else {
        clearError('newPasswordError');
    }

    if (newPassword !== confirmPassword) {
        showError('confirmPasswordError', 'Mật khẩu xác nhận không khớp');
        return false;
    } else {
        clearError('confirmPasswordError');
    }
    return true;
}
</script>
</head>
<body>
<h2>Đặt lại mật khẩu</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/reset-password" method="post" onsubmit="return validateResetPasswordForm()">
    <label>Mã OTP đã gửi:</label><br>
    <input type="text" name="otp" id="otp" required>
    <span id="otpError" style="color:red; display:none;"></span><br><br>
    <label>Mật khẩu mới:</label><br>
    <input type="password" name="newPassword" id="newPassword" required>
    <span id="newPasswordError" style="color:red; display:none;"></span><br><br>
    <label>Xác nhận mật khẩu:</label><br>
    <input type="password" name="confirmPassword" id="confirmPassword" required>
    <span id="confirmPasswordError" style="color:red; display:none;"></span><br><br>
    <input type="submit" value="Đặt lại mật khẩu">
</form>
</body>
</html>