<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head><title>Book Form</title></head>
<body>
<h1>${empty book ? 'Thêm sách' : 'Cập nhật sách'}</h1>
<form method="post" action="${pageContext.request.contextPath}/admin/books" class="auth-form">
    <input type="hidden" name="action" value="${empty book ? 'create' : 'edit'}">
    <input type="hidden" name="bookId" value="${book.bookId}">

    <label>ISBN</label><input type="number" name="isbn" value="${book.isbn}" required>
    <label>Title</label><input type="text" name="title" value="${book.title}" required>
    <label>Author</label>
    <select name="authorId" required>
        <c:forEach var="author" items="${authors}">
            <option value="${author.authorId}" ${author.authorName == book.authorName ? 'selected' : ''}>${author.authorName}</option>
        </c:forEach>
    </select>
    <label>Publisher</label><input type="text" name="publisher" value="${book.publisher}" required>
    <label>Price</label><input type="number" step="0.01" name="price" value="${empty book ? '0.00' : book.price}" required>
    <label>Publish date</label><input type="date" name="publishDate" value="${book.publishDate}" required>
    <label>Cover image</label>
    <select name="coverImage">
        <option value="book-1.jpg" ${book.coverImage == 'book-1.jpg' ? 'selected' : ''}>book-1.jpg</option>
        <option value="book-2.jpg" ${book.coverImage == 'book-2.jpg' ? 'selected' : ''}>book-2.jpg</option>
        <option value="book-3.jpg" ${book.coverImage == 'book-3.jpg' ? 'selected' : ''}>book-3.jpg</option>
    </select>
    <label>Quantity</label><input type="number" name="quantity" value="${book.quantity}" required>
    <button type="submit">Lưu</button>
</form>
<p><a href="${pageContext.request.contextPath}/admin/books">Quay lại</a></p>
</body>
</html>
