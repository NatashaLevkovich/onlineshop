<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="i18n"/>
<c:url var="path" value="/shop?page=${sessionScope.pageName}"/>
<div class="header">

    <div class="header-top">

        <div class="logo"><a href="${pageContext.request.contextPath}/shop?page=main">
            <span class="baby_sh">BABY SH</span>
            <img src="image/logo.png">
            <span class="p">P</span></a>
        </div>

        <div class="search">
            <form id="search"><input type="search" value="<fmt:message bundle="${i18n}" key="header.productsearch"/>">
                <input type="button" class="button-search" src="image/baseline_search_black_18dp.png"
                       value="<fmt:message bundle="${i18n}" key="header.search"/>">
            </form>
        </div>

        <c:set value="${user}" var="user" scope="session"/>

        <div>
            <c:if test="${empty user}">
                <div class="si-in">
                    <p class="in"><a href="${pageContext.request.contextPath}/shop?page=login"><fmt:message bundle="${i18n}"
                                                                                key="header.signin"/></a></p>
                    <span class="line_h"></span>
                    <p class="reg"><a href="${pageContext.request.contextPath}/shop?page=registration"><fmt:message bundle="${i18n}"
                                                                                  key="header.reg"/></a></p>
                </div>
            </c:if>

            <c:if test="${not empty user}">
                <div class="logout">
                    <p class="in_mail"><a href="${pageContext.request.contextPath}/shop?page=orders">${user.email}</a></p>
                    <span class="line_h1"></span>
                    <p class="out"><a href="${pageContext.request.contextPath}/shop?page=main&logout=off"><fmt:message bundle="${i18n}" key="header.logout"/></a></p>
                </div>
            </c:if>

            <div class="login">
                <a href="javascript:LoginHide()"><img src="image/baseline_clear_black_18dp.png"/></a>
                <h2><fmt:message bundle="${i18n}" key="header.welcome"/></h2>
                <h3><fmt:message bundle="${i18n}" key="header.enter"/></h3>
                <p id="error">Неверный логин или пароль</p>
                <form id="login_form" action="">
                    <div class="lo">
                        <input class="mail" type="email" name="email">
                    </div>
                    <div class="par">
                        <input class="pass" type="password" name="password">
                    </div>
                    <input type="button" class="button_login"
                           value="<fmt:message bundle="${i18n}" key="header.signin2"/>"/>
                </form>
                <a class="registration" href="${pageContext.request.contextPath}/shop?page=registration"><fmt:message bundle="${i18n}"
                                                                                    key="header.reg"/></a>
                <a class="remember" href="#"><fmt:message bundle="${i18n}" key="header.forgot"/></a>
            </div>


            <%--<div>--%>
            <%--<c:choose>--%>
            <%--<c:when test="${not empty user}">--%>
            <%--<p class="logout"><a href="#">${user.email}</a> | <a href="/shop?page=main&logout=off">Выйти</a></p>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--&lt;%&ndash;<p class="registration"><a href="/shop?page=login">Вход</a> | <a href="/shop?page=registration">Регистрация</a></p>&ndash;%&gt;--%>

            <%----%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--</div>--%>

            <c:set value="${items}" var="itemscount" scope="session"/>

            <c:if test="${empty itemscount}">
                <c:set value="0" var="itemscount"/>
            </c:if>

            <div class="Sh_c">
                <p class="e_shop"><fmt:message bundle="${i18n}" key="header.onshop"/></p>
                <c:if test="${not empty user}">
                    <a href="${pageContext.request.contextPath}/shop?page=shoppingbasket">
                        <span class="basket"><fmt:message bundle="${i18n}" key="header.basket"/></span>
                        <img class="basket-img" src="image/baseline_shopping_basket_black_18dp.png"/>
                        <span class="ellipse"></span>
                        <span class="count_item">${itemscount}</span>
                    </a>
                </c:if>
                <%--<c:if test="${empty user}">--%>
                <%--<a href="javascript:LoginShow()">--%>
                <%--<span class="basket">Корзина</span>--%>
                <%--<img class="basket-img" src="image/baseline_shopping_basket_black_18dp.png"/>--%>
                <%--<span class="ellipse"></span>--%>
                <%--<span class="count_item">${itemscount}</span>--%>
                <%--</a>--%>
                <%--</c:if>--%>
            </div>
        </div>
    </div>

    <div class="menu">

        <ul>
            <li class="select_menu"><a href="${pageContext.request.contextPath}/shop?page=main"><fmt:message bundle="${i18n}" key="header.main"/></a></li>
            <li class="catalog"><a href="${pageContext.request.contextPath}/shop?page=catalog"><fmt:message bundle="${i18n}" key="header.catalog"/></a></li>
            <div class="submenu">
                <ul class="select">
                    <li class="for-boys"><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=boys"><fmt:message bundle="${i18n}" key="header.cat2"/></a></li>
                    <li class="for-girls"><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=girls"><fmt:message bundle="${i18n}" key="header.cat3"/></a></li>
                    <li class="for-newborn"><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=newborn"><fmt:message bundle="${i18n}" key="header.cat1"/></a></li>
                    <%--<li><a href="#">Нижнее белье</a></li>--%>
                </ul>
            </div>
            </li>
            <li class="delivery"><a href="#"><fmt:message bundle="${i18n}" key="header.delivery"/></a></li>
            <li class="pay"><a href="#"><fmt:message bundle="${i18n}" key="header.pay"/></a></li>
            <li class="about-us"><a href="#"><fmt:message bundle="${i18n}" key="header.us"/></a></li>
            <c:if test="${not empty user and user.status eq 'admin'}">
                <li class="admin"><a href="${pageContext.request.contextPath}/shop?page=admin">Страница администратора</a></li>
            </c:if>
            <li class="ru"><a href="${path}&amp;locale=ru">RU</a></li>
            <li class="en"><a href="${path}&amp;locale=en">EN</a></li>
        </ul>


    </div>
</div>
<!-- END of header -->