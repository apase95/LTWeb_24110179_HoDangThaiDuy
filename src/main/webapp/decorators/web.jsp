<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:title default="Trang chủ"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <%@ include file="/commons/web/header.jsp" %>

        <div class="row">
            <div class="col">
                <sitemesh:write property="body"/>
            </div>
        </div>

        <%@ include file="/commons/web/footer.jsp" %>
    </div>
</body>
</html>