<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng ký</title>
<script>
function validateRegisterForm() {
    let valid = true;
    const fullname = document.getElementById('fullname').value;
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const phone = document.getElementById('phone').value;

    // Validate fullname
    if (!validateRequired(fullname)) {
        showError('fullnameError', 'Họ tên không được để trống');
        valid = false;
    } else {
        clearError('fullnameError');
    }

    // Validate username
    if (!validateRequired(username)) {
        showError('usernameError', 'Username không được để trống');
        valid = false;
    } else if (username.length < 3) {
        showError('usernameError', 'Username ít nhất 3 ký tự');
        valid = false;
    } else {
        clearError('usernameError');
    }

    // Validate email
    if (!validateRequired(email)) {
        showError('emailError', 'Email không được để trống');
        valid = false;
    } else if (!validateEmail(email)) {
        showError('emailError', 'Email không hợp lệ');
        valid = false;
    } else {
        clearError('emailError');
    }

    // Validate password
    if (!validateRequired(password)) {
        showError('passwordError', 'Mật khẩu không được để trống');
        valid = false;
    } else if (!validatePassword(password)) {
        showError('passwordError', 'Mật khẩu ít nhất 6 ký tự');
        valid = false;
    } else {
        clearError('passwordError');
    }

    // Validate phone (optional)
    if (phone && !validatePhone(phone)) {
        showError('phoneError', 'Số điện thoại không hợp lệ (10-11 số)');
        valid = false;
    } else {
        clearError('phoneError');
    }

    return valid;
}
</script>
</head>
<body>
    <h2>Tạo tài khoản mới</h2>
    
    <c:if test="${alert != null}">
        <h3 style="color:red;">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validateRegisterForm()">
        <label>Họ tên:</label>
        <input type="text" name="fullname" id="fullname" value="${param.fullname}" required>
        <span id="fullnameError" style="color:red; display:none;"></span><br><br>
        
        <label>Username:</label>
        <input type="text" name="username" id="username" value="${param.username}" required>
        <span id="usernameError" style="color:red; display:none;"></span><br><br>
        
        <label>Email:</label>
        <input type="email" name="email" id="email" value="${param.email}" required>
        <span id="emailError" style="color:red; display:none;"></span><br><br>
        
        <label>Mật khẩu:</label>
        <input type="password" name="password" id="password" required>
        <span id="passwordError" style="color:red; display:none;"></span><br><br>
        
        <label>Số điện thoại:</label>
        <input type="text" name="phone" id="phone" value="${param.phone}">
        <span id="phoneError" style="color:red; display:none;"></span><br><br>
        
        <button type="submit">Tạo tài khoản</button>
    </form>
    <p>Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
</body>
</html>