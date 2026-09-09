<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Admin</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
        <span class="me-3">Xin chào, ${sessionScope.account.fullName}</span>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-sm btn-outline-secondary">
            <i class="fas fa-sign-out-alt"></i> Đăng xuất
        </a>
    </div>
</div>