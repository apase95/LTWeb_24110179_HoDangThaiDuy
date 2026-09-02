<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng ký</title></head>
<body>
    <h2>Tạo tài khoản mới</h2>
    
    <!-- Hiển thị cảnh báo nếu có -->
    <c:if test="${alert != null}">
        <h3 style="color:red;">${alert}</h3>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <label>Họ tên:</label>
        <input type="text" name="fullname" required><br><br>
        
        <label>Username:</label>
        <input type="text" name="username" required><br><br>
        
        <label>Email:</label>
        <input type="email" name="email" required><br><br>
        
        <label>Mật khẩu:</label>
        <input type="password" name="password" required><br><br>
        
        <label>Số điện thoại:</label>
        <input type="text" name="phone"><br><br>
        
        <button type="submit">Tạo tài khoản</button>
    </form>
    <p>Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
</body>
</html>