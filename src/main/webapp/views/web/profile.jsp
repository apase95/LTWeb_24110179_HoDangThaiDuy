<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" buffer="16kb" autoFlush="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hồ sơ của tôi</title>
    <script src="${pageContext.request.contextPath}/js/validation.js"></script>
    <script>
        function validateProfileForm() {
            const fullname = document.getElementById('fullname').value;
            const phone = document.getElementById('phone').value;
            if (!validateRequired(fullname)) {
                showError('fullnameError', 'Họ tên không được để trống');
                return false;
            } else {
                clearError('fullnameError');
            }
            if (phone && !validatePhone(phone)) {
                showError('phoneError', 'Số điện thoại không hợp lệ (10-11 số)');
                return false;
            } else {
                clearError('phoneError');
            }
            return true;
        }
    </script>
</head>
<body>
    <%@ include file="/commons/web/header.jsp" %>
    <main class="container my-4">
        <h2>Thông tin cá nhân</h2>
        <c:if test="${not empty message}">
            <p style="color:green;">${message}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data" onsubmit="return validateProfileForm()">
            <label>Username:</label>
            <input type="text" value="${user.username}" disabled><br><br>
            <label>Email:</label>
            <input type="text" value="${user.email}" disabled><br><br>
            <label>Họ tên:</label>
            <input type="text" name="fullname" id="fullname" value="${user.fullname}" required>
            <span id="fullnameError" style="color:red; display:none;"></span><br><br>
            <label>Số điện thoại:</label>
            <input type="text" name="phone" id="phone" value="${user.phone}">
            <span id="phoneError" style="color:red; display:none;"></span><br><br>
            <label>Ảnh đại diện hiện tại:</label><br>
            <c:choose>
                <c:when test="${user.avatar != null && user.avatar.startsWith('http')}">
                    <img src="${user.avatar}" height="100" />
                </c:when>
                <c:when test="${user.avatar != null}">
                    <img src="${pageContext.request.contextPath}/image?fname=${user.avatar}" height="100" />
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/image?fname=default.png" height="100" />
                </c:otherwise>
            </c:choose>
            <br><br>
            <label>Chọn ảnh mới:</label>
            <input type="file" name="avatar"><br><br>
            <input type="submit" value="Cập nhật">
        </form>
        <a href="${pageContext.request.contextPath}/home">Quay lại trang chủ</a>
    </main>
    <%@ include file="/commons/web/footer.jsp" %>
</body>
</html>