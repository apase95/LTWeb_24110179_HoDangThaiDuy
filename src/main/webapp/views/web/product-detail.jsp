<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Chi tiết sản phẩm</title></head>
<body>
<h2>Chi tiết sản phẩm</h2>
<div style="border: 1px solid #ccc; padding: 20px;">
    <c:choose>
        <c:when test="${product.images.startsWith('http')}">
            <img height="200" src="${product.images}" />
        </c:when>
        <c:otherwise>
            <img height="200" src="${pageContext.request.contextPath}/image?fname=${product.images}" />
        </c:otherwise>
    </c:choose>
    <h3>${product.productName}</h3>
    <p><strong>Giá:</strong> ${product.price} VND</p>
    <p><strong>Số lượng:</strong> ${product.quantity}</p>
    <p><strong>Danh mục:</strong> ${product.category.categoryName}</p>
    <p><strong>Mô tả:</strong> ${product.description}</p>
    <a href="${pageContext.request.contextPath}/product">Quay lại danh sách</a> |
    <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
</div>
</body>
</html>