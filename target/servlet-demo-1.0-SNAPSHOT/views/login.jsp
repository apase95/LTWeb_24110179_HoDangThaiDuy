<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng nhập</title></head>
<body>
    <h2>Đăng Nhập Vào Hệ Thống</h2>
    
    <!-- Hiển thị cảnh báo nếu có -->
    <c:if test="${alert != null}">
        <h3 style="color:red;">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Username:</label>
        <input type="text" name="username" required><br><br>
        
        <label>Password:</label>
        <input type="password" name="password" required><br><br>
        
        <input type="checkbox" name="remember"> Nhớ tôi<br><br>
        
        <button type="submit">Đăng nhập</button>
    </form>
    <p>Nếu bạn chưa có tài khoản, hãy <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
    <p><a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a></p>
</body>
</html>