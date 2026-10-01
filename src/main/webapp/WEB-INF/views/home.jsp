<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>
<h1>${message}</h1>
<p class="error">${error}</p>

<c:forEach var="group" items="${authorBooks}">
    <section class="author-section">
        <h2>Tác giả: ${group.authorName}</h2>
        <div class="book-grid">
            <c:forEach var="book" items="${group.books}">
                <article class="book-card">
                    <img src="${pageContext.request.contextPath}/assets/images/${book.coverImage}" alt="${book.title}">
                    <p>Tiêu đề: <a href="${pageContext.request.contextPath}/book-detail?id=${book.bookId}">${book.title}</a></p>
                    <p>Mã isbn: ${book.isbn}</p>
                    <p>Tác giả: ${book.authorName}</p>
                    <p>Publisher: ${book.publisher}</p>
                    <p>Publisher_date: <fmt:formatDate value="${book.publishDate}" pattern="dd/MM/yyyy" /></p>
                    <p>Quantity: ${book.quantity}</p>
                    <p>Review (${book.reviewCount})</p>
                    <form method="post" action="${pageContext.request.contextPath}/cart">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="bookId" value="${book.bookId}">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit">Thêm vào giỏ</button>
                    </form>
                </article>
            </c:forEach>
        </div>
    </section>
</c:forEach>

<nav class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/home?page=${currentPage - 1}">Trang trước</a>
    </c:if>
    <c:forEach begin="1" end="${totalPages}" var="pageNumber">
        <a class="${pageNumber == currentPage ? 'active' : ''}" href="${pageContext.request.contextPath}/home?page=${pageNumber}">${pageNumber}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/home?page=${currentPage + 1}">Trang sau</a>
    </c:if>
</nav>
</body>
</html>
