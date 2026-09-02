<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách danh mục</title>
</head>
<body>
<h2>Quản lý danh mục</h2>
<a href="<c:url value='/admin/category/add'/>">Thêm danh mục</a>
<hr>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Hình ảnh</th>
        <th>Tên danh mục</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>
            <td>
                <c:choose>
                    <c:when test="${cate.images.startsWith('http')}">
                        <img height="100" src="${cate.images}" />
                    </c:when>
                    <c:otherwise>
                        <img height="100" src="<c:url value='/image?fname=${cate.images}'/>" />
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${cate.categoryName}</td>
            <td>
                <c:if test="${cate.status == 1}">Hoạt động</c:if>
                <c:if test="${cate.status != 1}">Khóa</c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a> |
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>