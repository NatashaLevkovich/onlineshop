<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main">
    <h2>Добро пожаловать!</h2>
    <form id="login-form" action="${pageContext.request.contextPath}/shop?page=login" method="post">
        <p>E-mail</p>
        <c:set scope="request" var="error" value="${error}"/>
        <c:out value="${error}"/>
        <br/>
        <input type="email" name="email" required>
        <p>Пароль</p>
        <input type="password" name="password" required><br/>
        <button form="login-form">Войти</button>
    </form>
</div>