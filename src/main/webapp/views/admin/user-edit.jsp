<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Sửa người dùng</title></head>
<body>
<%@ include file="/commons/admin/header.jsp" %>
<h1>Sửa người dùng</h1>
<form action="<c:url value='/admin/users/update'/>" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label>Username:</label> ${user.username}<br>
    <label>Email:</label> ${user.email}<br>
    <label>Họ tên:</label><input type="text" name="fullname" value="${user.fullname}"><br>
    <label>SĐT:</label><input type="text" name="phone" value="${user.phone}"><br>
    <label>Vai trò:</label>
    <select name="roleid">
        <option value="1" ${user.roleid == 1 ? 'selected' : ''}>Admin</option>
        <option value="2" ${user.roleid == 2 ? 'selected' : ''}>Manager</option>
        <option value="3" ${user.roleid == 3 ? 'selected' : ''}>User</option>
    </select><br>
    <label>Kích hoạt:</label>
    <input type="radio" name="active" value="true" ${user.active ? 'checked' : ''}> Có
    <input type="radio" name="active" value="false" ${!user.active ? 'checked' : ''}> Không<br>
    <input type="submit" value="Cập nhật">
</form>
<p><a href="<c:url value='/admin/users'/>">Quay lại danh sách</a></p>
<%@ include file="/commons/admin/footer.jsp" %>
</body>
</html>