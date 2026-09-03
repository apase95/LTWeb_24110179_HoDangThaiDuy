<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<h2>Quản lý sản phẩm</h2>
<a href="${pageContext.request.contextPath}/admin/product/add">Thêm sản phẩm</a>
<hr>
<table border="1" width="100%">
    <tr>
        <th>ID</th>
        <th>Hình ảnh</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Danh mục</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach items="${listproduct}" var="p" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>
            <td>
                <c:choose>
                    <c:when test="${p.images.startsWith('http')}">
                        <img height="100" src="${p.images}" />
                    </c:when>
                    <c:otherwise>
                        <img height="100" src="${pageContext.request.contextPath}/image?fname=${p.images}" />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td>${p.category.categoryName}</td>
            <td>
                <c:if test="${p.status == 1}">Hoạt động</c:if>
                <c:if test="${p.status != 1}">Khóa</c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.productId}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.productId}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>