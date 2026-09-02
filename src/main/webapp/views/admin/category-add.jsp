<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Thêm danh mục</title></head>
<body>
<h2>Thêm danh mục mới</h2>
<form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
    <label>Tên danh mục:</label><br>
    <input type="text" name="categoryname" required><br><br>

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