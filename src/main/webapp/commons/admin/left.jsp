<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav flex-column p-3">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">
            <i class="fas fa-tachometer-alt"></i> Dashboard
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/categories">
            <i class="fas fa-list"></i> Danh mục
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/admin/products">
            <i class="fas fa-box"></i> Sản phẩm
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/users">
            <i class="fas fa-users"></i> Người dùng
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
            <i class="fas fa-sign-out-alt"></i> Đăng xuất
        </a>
    </li>
</ul>