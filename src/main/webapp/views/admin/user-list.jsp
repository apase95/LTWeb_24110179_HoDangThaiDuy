<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Danh sách người dùng</title></head>
<body>
<%@ include file="/commons/admin/header.jsp" %>
<h1>Quản lý người dùng</h1>
<a href="<c:url value='/admin/users/add'/>">Thêm người dùng</a>
<form action="<c:url value='/admin/users'/>" method="get">
    <input type="text" name="keyword" placeholder="Tìm kiếm..." value="${keyword}">
    <button type="submit">Tìm</button>
    <c:if test="${not empty keyword}">
        <a href="<c:url value='/admin/users'/>">Xóa tìm kiếm</a>
    </c:if>
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Họ tên</th>
        <th>SĐT</th>
        <th>Vai trò</th>
        <th>Kích hoạt</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.fullname}</td>
            <td>${u.phone}</td>
            <td>${u.roleid == 1 ? 'Admin' : u.roleid == 2 ? 'Manager' : 'User'}</td>
            <td>${u.active ? 'Đã kích hoạt' : 'Chưa kích hoạt'}</td>
            <td>
                <a href="<c:url value='/admin/users/edit/${u.id}'/>">Sửa</a> |
                <a href="<c:url value='/admin/users/delete/${u.id}'/>" onclick="return confirm('Xóa?')">Xóa</a> |
                <c:choose>
                    <c:when test="${u.active}">
                        <a href="<c:url value='/admin/users/disable/${u.id}'/>">Vô hiệu</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/admin/users/enable/${u.id}'/>">Kích hoạt</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/commons/admin/footer.jsp" %>
</body>
</html>