<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><decorator:title default="Admin - Book Store" /></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <decorator:head />
</head>
<body>
<header class="site-header admin-header">
    <strong>Book Store Admin</strong>
    <nav>
        <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/home">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
        <a href="${pageContext.request.contextPath}/orders">Lịch sử đơn hàng</a>
        <c:choose>
            <c:when test="${empty sessionScope.currentUser}">
                <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/admin">Trang quản trị</a>
        <a href="${pageContext.request.contextPath}/admin/books">Quản lý sách</a>
    </nav>
</header>
<main class="container">
    <decorator:body />
</main>
<footer class="site-footer">
    Họ tên: Hồ Đặng Thái Duy | MSSV: 24110179 | Mã đề: 02
</footer>
</body>
</html>
