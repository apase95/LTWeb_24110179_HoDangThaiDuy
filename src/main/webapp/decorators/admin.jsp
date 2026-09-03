<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:title default="Admin"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 bg-light">
                <%@ include file="/commons/admin/left.jsp" %>
            </div>
            <div class="col-md-10">
                <%@ include file="/commons/admin/header.jsp" %>
                <sitemesh:write property="body"/>
                <%@ include file="/commons/admin/footer.jsp" %>
            </div>
        </div>
    </div>
</body>
</html>