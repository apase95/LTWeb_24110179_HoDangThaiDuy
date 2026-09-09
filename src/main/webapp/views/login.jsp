<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng nhập</title>
<script>
function validateLoginForm() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!validateRequired(username)) {
        showError('usernameError', 'Username không được để trống');
        return false;
    } else {
        clearError('usernameError');
    }

    if (!validateRequired(password)) {
        showError('passwordError', 'Mật khẩu không được để trống');
        return false;
    } else {
        clearError('passwordError');
    }
    return true;
}
</script>
</head>
<body>
    <h2>Đăng Nhập Vào Hệ Thống</h2>
    
    <c:if test="${alert != null}">
        <h3 style="color:red;">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="return validateLoginForm()">
        <label>Username:</label>
        <input type="text" name="username" id="username" value="${param.username}" required>
        <span id="usernameError" style="color:red; display:none;"></span><br><br>
        
        <label>Password:</label>
        <input type="password" name="password" id="password" required>
        <span id="passwordError" style="color:red; display:none;"></span><br><br>
        
        <input type="checkbox" name="remember"> Nhớ tôi<br><br>
        
        <button type="submit">Đăng nhập</button>
    </form>
    <p>Nếu bạn chưa có tài khoản, hãy <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
    <p><a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a></p>
</body>
</html>