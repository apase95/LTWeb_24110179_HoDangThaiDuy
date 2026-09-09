<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" buffer="16kb" autoFlush="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Chi tiết sản phẩm</title></head>
<body>
    <%@ include file="/commons/web/header.jsp" %>
    <main class="container my-4">
        <div class="row">
            <div class="col-md-6">
                <c:choose>
                    <c:when test="${product.images.startsWith('http')}">
                        <img src="${product.images}" class="img-fluid" alt="${product.productName}" style="max-height:400px; object-fit:contain; width:100%;">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/image?fname=${product.images}" class="img-fluid" alt="${product.productName}" style="max-height:400px; object-fit:contain; width:100%;">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-6">
                <h2>${product.productName}</h2>
                <p><strong>Giá:</strong> ${product.price} VND</p>
                <p><strong>Số lượng:</strong> ${product.quantity}</p>
                <p><strong>Danh mục:</strong> ${product.category.categoryName}</p>
                <p><strong>Mô tả:</strong> ${product.description}</p>
                <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary">Quay lại</a>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-primary">Trang chủ</a>
            </div>
        </div>
    </main>
    <%@ include file="/commons/web/footer.jsp" %>
</body>
</html>