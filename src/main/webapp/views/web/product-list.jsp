<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<h2 class="mb-4">Tất cả sản phẩm</h2>
<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
    <c:forEach items="${products}" var="p">
        <div class="col">
            <div class="card h-100 product-card">
                <c:choose>
                    <c:when test="${p.images.startsWith('http')}">
                        <img src="${p.images}" class="card-img-top" alt="${p.productName}" style="height: 200px; object-fit: cover;">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/image?fname=${p.images}" class="card-img-top" alt="${p.productName}" style="height: 200px; object-fit: cover;">
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h5 class="card-title">${p.productName}</h5>
                    <p class="card-text"><strong>${p.price} VND</strong></p>
                </div>
                <div class="card-footer bg-transparent border-top-0">
                    <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="btn btn-primary btn-sm">Xem chi tiết</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Phân trang -->
<div class="mt-4 d-flex justify-content-between align-items-center">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage - 1}" class="btn btn-outline-secondary">« Trước</a>
    </c:if>
    <span>Trang ${currentPage} / ${totalPages}</span>
    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/product?page=${currentPage + 1}" class="btn btn-outline-secondary">Sau »</a>
    </c:if>
</div>
</body>
</html>