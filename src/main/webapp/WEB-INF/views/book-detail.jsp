<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <title>${detail.book.title}</title>
</head>
<body>
<section class="detail-box">
    <img src="${pageContext.request.contextPath}/assets/images/${detail.book.coverImage}" alt="${detail.book.title}">
    <div>
        <p>Tiêu đề: ${detail.book.title}</p>
        <p>Mã isbn: ${detail.book.isbn}</p>
        <p>Tác giả: ${detail.book.authorName}</p>
        <p>Publisher: ${detail.book.publisher}</p>
        <p>Publisher_date: <fmt:formatDate value="${detail.book.publishDate}" pattern="dd/MM/yyyy" /></p>
        <p>Quantity: ${detail.book.quantity}</p>
        <p>Reviews (${detail.book.reviewCount})</p>
        <form method="post" action="${pageContext.request.contextPath}/cart">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="bookId" value="${detail.book.bookId}">
            <input type="number" name="quantity" min="1" max="${detail.book.quantity}" value="1">
            <button type="submit">Thêm vào giỏ</button>
        </form>
    </div>
</section>

<h2>Reviews</h2>
<c:forEach var="review" items="${detail.reviews}">
    <p>[${review.fullname}]: ${review.reviewText}</p>
</c:forEach>

<h2>Form thêm reviews</h2>
<form method="post" action="${pageContext.request.contextPath}/book-detail?id=${detail.book.bookId}" class="review-form">
    <textarea name="reviewText" required></textarea>
    <button type="submit">Submit</button>
</form>
</body>
</html>
