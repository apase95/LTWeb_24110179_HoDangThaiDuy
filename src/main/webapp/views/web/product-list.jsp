<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách sản phẩm</title></head>
<body>
<h2>Tất cả sản phẩm</h2>
<div style="display: flex; flex-wrap: wrap; gap: 20px;">
    <c:forEach items="${products}" var="p">
        <div style="border: 1px solid #ccc; padding: 15px; width: 200px;">
            <c:choose>
                <c:when test="${p.images.startsWith('http')}">
                    <img height="150" width="150" src="${p.images}" />
                </c:when>
                <c:otherwise>
                    <img height="150" width="150" src="${pageContext.request.contextPath}/image?fname=${p.images}" />
                </c:otherwise>
            </c:choose>
            <h4>${p.productName}</h4>
            <p>Giá: ${p.price} VND</p>
            <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}">Xem chi tiết</a>
        </div>
    </c:forEach>
</div>

<div style="margin-top: 20px;">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">Trước</a>
    </c:if>
    <span>Trang ${currentPage} / ${totalPages}</span>
    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">Sau</a>
    </c:if>
</div>
</body>
</html>