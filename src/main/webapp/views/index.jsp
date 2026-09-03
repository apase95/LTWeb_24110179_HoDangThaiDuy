<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Trang chủ</title></head>
<body>
    <h1>Chào mừng đến với trang chủ!</h1>
    <h3>Sản phẩm mới nhất</h3>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <c:forEach items="${newProducts}" var="p">
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
    <a href="${pageContext.request.contextPath}/product">Xem tất cả sản phẩm</a>
</body>
</html>