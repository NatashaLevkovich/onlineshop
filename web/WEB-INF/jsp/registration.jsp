<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main">

        <c:set var="reg" scope="request" value="${registration}"/>
        <c:out value="${reg}"/>

        <form id="registration-form" action="/shop?page=registration" method="post">
        <p>E-mail:</p>
        <input type="email" name="email" requared>
        <p>Пароль:</p>
        <input type="password" name="pass" requared>
        <p>Повторите пароль:</p>
        <input type="password" name="pass2" requared>
        <p>Имя:</p>
        <input type="text" name="firstname">
        <p>Фамилия:</p>
        <input type="text" name="lastname">
        <p>Адрес:</p>
        <input type="text" name="address">
        <p>Телефон:</p>
        <input type="tel" name="phone">
                <button type="submit" form="registration-form" >Зарегистрироваться</button>
    </form>
</div>
