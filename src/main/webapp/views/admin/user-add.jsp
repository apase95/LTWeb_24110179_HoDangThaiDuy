<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Thêm người dùng</title></head>
<body>
<%@ include file="/commons/admin/header.jsp" %>
<h1>Thêm người dùng</h1>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
<form action="<c:url value='/admin/users/insert'/>" method="post">
    <label>Tên đăng nhập:</label>
    <input type="text" name="username" value="${user.username}" required><br><br>

    <label>Email:</label>
    <input type="email" name="email" value="${user.email}" required><br><br>

    <label>Mật khẩu:</label>
    <input type="password" name="password" required minlength="6"><br><br>

    <label>Họ tên:</label>
    <input type="text" name="fullname" value="${user.fullname}" required><br><br>

    <label>Số điện thoại:</label>
    <input type="text" name="phone" value="${user.phone}"><br><br>

    <label>Vai trò:</label>
    <select name="roleid">
        <option value="1" ${user.roleid == 1 ? 'selected' : ''}>Admin</option>
        <option value="2" ${user.roleid == 2 ? 'selected' : ''}>Manager</option>
        <option value="3" ${user.roleid == 3 ? 'selected' : ''}>User</option>
    </select><br><br>

    <label>Trạng thái:</label>
    <input type="radio" name="active" value="true" ${user.active ? 'checked' : ''}> Kích hoạt
    <input type="radio" name="active" value="false" ${!user.active ? 'checked' : ''}> Vô hiệu
    <br><br>

    <button type="submit">Tạo người dùng</button>
</form>
<p><a href="<c:url value='/admin/users'/>">Quay lại danh sách</a></p>
<%@ include file="/commons/admin/footer.jsp" %>
</body>
</html>
