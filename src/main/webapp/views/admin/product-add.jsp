<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Thêm sản phẩm</title>
<script>
function validateProductForm() {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('price').value;
    const quantity = document.getElementById('quantity').value;

    if (!validateRequired(name)) {
        alert('Tên sản phẩm không được để trống');
        return false;
    }
    if (isNaN(price) || parseFloat(price) <= 0) {
        alert('Giá phải là số dương');
        return false;
    }
    if (isNaN(quantity) || parseInt(quantity) < 0) {
        alert('Số lượng phải là số nguyên không âm');
        return false;
    }
    return true;
}
</script>
</head>
<body>
<h2>Thêm sản phẩm mới</h2>
<form action="${pageContext.request.contextPath}/admin/product/insert" method="post" enctype="multipart/form-data" onsubmit="return validateProductForm()">
    <label>Tên sản phẩm:</label><br>
    <input type="text" name="productName" id="productName" required><br><br>

    <label>Giá:</label><br>
    <input type="number" name="price" id="price" step="0.01" required><br><br>

    <label>Số lượng:</label><br>
    <input type="number" name="quantity" id="quantity" required><br><br>

    <label>Mô tả:</label><br>
    <textarea name="description" rows="4" cols="50"></textarea><br><br>

    <label>Ảnh:</label><br>
    <input type="file" name="images"><br><br>

    <label>Danh mục:</label><br>
    <select name="categoryId">
        <c:forEach items="${categories}" var="c">
            <option value="${c.categoryId}">${c.categoryName}</option>
        </c:forEach>
    </select><br><br>

    <label>Trạng thái:</label><br>
    <input type="radio" name="status" value="1" checked> Hoạt động
    <input type="radio" name="status" value="0"> Khóa
    <br><br>

    <input type="submit" value="Thêm">
</form>
</body>
</html>