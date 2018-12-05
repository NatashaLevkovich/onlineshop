<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Детская одежда</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="script/script.js"></script>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/footer.css">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa|Open+Sans" rel="stylesheet">
</head>
<body>

<%@include file="WEB-INF/jsp/header.jsp" %>

<c:url var="bodyUrl" value="WEB-INF/jsp/main.jsp"></c:url>
<c:if test="${not empty sessionScope.pagePath}">
    <c:url var="bodyUrl" value="WEB-INF/jsp/${sessionScope.pagePath}"></c:url>
</c:if>
<jsp:include page="${bodyUrl}"></jsp:include>


<%@include file="WEB-INF/jsp/footer.jsp" %>

</body>
</html>