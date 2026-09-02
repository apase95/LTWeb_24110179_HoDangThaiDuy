<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Sửa danh mục</title></head>
<body>
<h2>Sửa danh mục</h2>
<form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="categoryid" value="${cate.categoryId}">

    <label>Tên danh mục:</label><br>
    <input type="text" name="categoryname" value="${cate.categoryName}" required><br><br>

    <label>Link ảnh (nếu có):</label><br>
    <input type="text" name="images" value="${cate.images}"><br><br>

    <label>Ảnh hiện tại:</label><br>
    <c:choose>
        <c:when test="${cate.images.startsWith('http')}">
            <img height="150" src="${cate.images}" />
        </c:when>
        <c:otherwise>
            <img height="150" src="<c:url value='/image?fname=${cate.images}'/>" />
        </c:otherwise>
    </c:choose>
    <br><br>

    <label>Upload ảnh mới:</label><br>
    <input type="file" name="images1"><br><br>

    <label>Trạng thái:</label><br>
    <input type="radio" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}> Hoạt động
    <input type="radio" name="status" value="0" ${cate.status != 1 ? 'checked' : ''}> Khóa
    <br><br>

    <input type="submit" value="Cập nhật">
</form>
</body>
</html>