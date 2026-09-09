<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" buffer="16kb" autoFlush="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <style>
        .product-card img { height: 200px; object-fit: cover; width: 100%; }
        .product-card .card-body { padding: 1rem; }
        .product-card .card-title { font-size: 1.1rem; font-weight: 500; margin-bottom: 0.5rem; }
        .product-card .card-text { font-size: 0.95rem; color: #dc3545; font-weight: 600; }
        .product-card { transition: transform 0.2s; }
        .product-card:hover { transform: scale(1.02); }
    </style>
</head>
<body>
    <%@ include file="/commons/web/header.jsp" %>
    <main class="container my-4">
        <h1 class="mb-4">Chào mừng đến với trang chủ!</h1>
        <h3 class="mb-3">Sản phẩm mới nhất</h3>
        <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
            <c:forEach items="${newProducts}" var="p">
                <div class="col">
                    <div class="card h-100 product-card">
                        <c:choose>
                            <c:when test="${p.images.startsWith('http')}">
                                <img src="${p.images}" class="card-img-top" alt="${p.productName}">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/image?fname=${p.images}" class="card-img-top" alt="${p.productName}">
                            </c:otherwise>
                        </c:choose>
                        <div class="card-body">
                            <h5 class="card-title">${p.productName}</h5>
                            <p class="card-text">${p.price} VND</p>
                        </div>
                        <div class="card-footer bg-transparent border-top-0">
                            <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="btn btn-primary btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/product" class="btn btn-outline-secondary">Xem tất cả sản phẩm</a>
        </div>
    </main>
    <%@ include file="/commons/web/footer.jsp" %>
</body>
</html>