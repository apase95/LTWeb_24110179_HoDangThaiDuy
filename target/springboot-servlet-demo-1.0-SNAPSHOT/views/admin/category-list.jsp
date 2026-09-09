<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" buffer="16kb" autoFlush="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách danh mục</title>
</head>
<body>
<%@ include file="/commons/admin/header.jsp" %>
<h2>Quản lý danh mục</h2>
<a href="<c:url value='/admin/categories/add'/>">Thêm danh mục</a>
<form action="<c:url value='/admin/categories'/>" method="get">
    <input type="search" name="keyword" value="${keyword}" placeholder="Tìm theo tên danh mục">
    <button type="submit">Tìm kiếm</button>
    <c:if test="${not empty keyword}">
        <a href="<c:url value='/admin/categories'/>">Xóa tìm kiếm</a>
    </c:if>
</form>
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
                <a href="<c:url value='/admin/categories/edit/${cate.categoryId}'/>">Sửa</a> |
                <a href="<c:url value='/admin/categories/delete/${cate.categoryId}'/>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/commons/admin/footer.jsp" %>
</body>
</html>