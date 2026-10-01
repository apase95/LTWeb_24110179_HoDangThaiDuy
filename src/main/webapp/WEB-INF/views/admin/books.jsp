<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head><title>Quản lý Books</title></head>
<body>
<h1>CRUD Books</h1>
<p><a class="button" href="${pageContext.request.contextPath}/admin/books?action=create">Thêm sách</a></p>

<table class="admin-table">
    <tr>
        <th>ID</th><th>ISBN</th><th>Title</th><th>Author</th><th>Publisher</th><th>Date</th><th>Quantity</th><th>Action</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.bookId}</td>
            <td>${book.isbn}</td>
            <td><a href="${pageContext.request.contextPath}/book-detail?id=${book.bookId}">${book.title}</a></td>
            <td>${book.authorName}</td>
            <td>${book.publisher}</td>
            <td><fmt:formatDate value="${book.publishDate}" pattern="dd/MM/yyyy" /></td>
            <td>${book.quantity}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/books?action=edit&id=${book.bookId}">Sửa</a>
                <form method="post" action="${pageContext.request.contextPath}/admin/books" class="inline-form">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${book.bookId}">
                    <button type="submit" onclick="return confirm('Xóa sách này?')">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<nav class="pagination">
    <c:if test="${currentPage > 1}"><a href="${pageContext.request.contextPath}/admin/books?page=${currentPage - 1}">Trang trước</a></c:if>
    <c:forEach begin="1" end="${totalPages}" var="p"><a class="${p == currentPage ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/books?page=${p}">${p}</a></c:forEach>
    <c:if test="${currentPage < totalPages}"><a href="${pageContext.request.contextPath}/admin/books?page=${currentPage + 1}">Trang sau</a></c:if>
</nav>
</body>
</html>
