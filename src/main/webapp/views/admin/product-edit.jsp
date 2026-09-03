<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Sửa sản phẩm</title></head>
<body>
<h2>Sửa sản phẩm</h2>
<form action="${pageContext.request.contextPath}/admin/product/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="productId" value="${product.productId}">

    <label>Tên sản phẩm:</label><br>
    <input type="text" name="productName" value="${product.productName}" required><br><br>

    <label>Giá:</label><br>
    <input type="number" name="price" step="0.01" value="${product.price}" required><br><br>

    <label>Số lượng:</label><br>
    <input type="number" name="quantity" value="${product.quantity}" required><br><br>

    <label>Mô tả:</label><br>
    <textarea name="description" rows="4" cols="50">${product.description}</textarea><br><br>

    <label>Ảnh hiện tại:</label><br>
    <c:choose>
        <c:when test="${product.images.startsWith('http')}">
            <img height="150" src="${product.images}" />
        </c:when>
        <c:otherwise>
            <img height="150" src="${pageContext.request.contextPath}/image?fname=${product.images}" />
        </c:otherwise>
    </c:choose>
    <br><br>

    <label>Chọn ảnh mới:</label><br>
    <input type="file" name="images"><br><br>

    <label>Danh mục:</label><br>
    <select name="categoryId">
        <c:forEach items="${categories}" var="c">
            <option value="${c.categoryId}" ${c.categoryId == product.category.categoryId ? 'selected' : ''}>
                ${c.categoryName}
            </option>
        </c:forEach>
    </select><br><br>

    <label>Trạng thái:</label><br>
    <input type="radio" name="status" value="1" ${product.status == 1 ? 'checked' : ''}> Hoạt động
    <input type="radio" name="status" value="0" ${product.status != 1 ? 'checked' : ''}> Khóa
    <br><br>

    <input type="submit" value="Cập nhật">
</form>
</body>
</html>