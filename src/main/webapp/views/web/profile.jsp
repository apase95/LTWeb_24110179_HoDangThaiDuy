<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hồ sơ của tôi</title>
</head>
<body>
    <h2>Thông tin cá nhân</h2>
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
        <label>Username:</label>
        <input type="text" value="${user.username}" disabled><br><br>

        <label>Email:</label>
        <input type="text" value="${user.email}" disabled><br><br>

        <label>Họ tên:</label>
        <input type="text" name="fullname" value="${user.fullname}" required><br><br>

        <label>Số điện thoại:</label>
        <input type="text" name="phone" value="${user.phone}"><br><br>

        <label>Ảnh đại diện hiện tại:</label><br>
        <c:choose>
            <c:when test="${user.avatar != null && user.avatar.startsWith('http')}">
                <img src="${user.avatar}" height="100" />
            </c:when>
            <c:when test="${user.avatar != null}">
                <img src="${pageContext.request.contextPath}/image?fname=${user.avatar}" height="100" />
            </c:when>
            <c:otherwise>
                <img src="${pageContext.request.contextPath}/image?fname=default.png" height="100" />
            </c:otherwise>
        </c:choose>
        <br><br>

        <label>Chọn ảnh mới:</label>
        <input type="file" name="avatar"><br><br>

        <input type="submit" value="Cập nhật">
    </form>
    <a href="${pageContext.request.contextPath}/home">Quay lại trang chủ</a>
</body>
</html>