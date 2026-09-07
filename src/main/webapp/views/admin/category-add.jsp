<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" buffer="16kb" autoFlush="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Thêm danh mục</title>
<script>
function validateCategoryForm() {
    const name = document.getElementById('categoryname').value;
    if (!validateRequired(name)) {
        alert('Tên danh mục không được để trống');
        return false;
    }
    return true;
}
</script>
</head>
<body>
<h2>Thêm danh mục mới</h2>
<form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data" onsubmit="return validateCategoryForm()">
    <label>Tên danh mục:</label><br>
    <input type="text" name="categoryname" id="categoryname" required><br><br>

    <label>Link ảnh (nếu có):</label><br>
    <input type="text" name="images"><br><br>

    <label>Upload ảnh:</label><br>
    <input type="file" name="images1"><br><br>

    <label>Trạng thái:</label><br>
    <input type="radio" name="status" value="1" checked> Hoạt động
    <input type="radio" name="status" value="0"> Khóa
    <br><br>

    <input type="submit" value="Thêm">
</form>
</body>
</html>