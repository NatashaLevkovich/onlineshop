<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="i18n"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main">

    <div class="promo1">
        <div class="text">
            <span class="span1">Только до конца осени</span>
            <span class="span2">скидка - 20%</span>
            <span class="span3">на всю oбувь для новорожденных</span>
        </div>
        <span class="v_cat">
    <a href="/shop?page=catalog"><fmt:message bundle="${i18n}" key="main.gotocat"/></a>
    </span>
    </div>

    <div class="popular-category">
        <h2 class="pop_cat"><span><fmt:message bundle="${i18n}" key="main.popcat"/></span></h2>
        <span class="line1"></span>
        <h3 class="pop1"><span></span><a href="#"><fmt:message bundle="${i18n}" key="main.pop1"/></a></h3>
        <h3 class="pop2"><a href="#"><fmt:message bundle="${i18n}" key="main.pop2"/></a></h3>
        <h3 class="pop3"><a href="#"><fmt:message bundle="${i18n}" key="main.pop3"/></a></h3>
        <h3 class="pop4"><a href="#"><fmt:message bundle="${i18n}" key="main.pop4"/></a></h3>

    </div>


    <h2 class="s_a"><span><fmt:message bundle="${i18n}" key="main.sale"/></span></h2>
    <span class="line2"></span>

    <div class="sale">

        <c:set var="count" value="0"/>
        <c:forEach var="saleproduct" items="${saleproduct}" varStatus="status">
                <div class="product-box-sale">
                    <img src="${saleproduct.image}">
                    <h3><a href="/shop?page=product&id=${saleproduct.id}">${saleproduct.name}</a></h3>
                    <p class="price"><fmt:formatNumber type="number" pattern="####.##" value="${saleproduct.price-(saleproduct.price*saleproduct.discount)}"/>
                        р.</p>
                    <p class="old-price">${saleproduct.price} р.</p>
                    <a class="buy" href="/shop?page=product&id=${saleproduct.id}"> <span>В корзину</span></a>
                    <p class="discount"><span>-${saleproduct.discount * 100}%</span></p>
                </div>
        </c:forEach>

    </div>


    <div class="promo2">
        <div class="text">
            <span class="span1">Только до конца осени</span>
            <span class="span2">скидка - 20%</span>
            <span class="span3">на костюмы супергероев</span>
        </div>
        <span class="v_cat">
    <a href="/shop?page=catalog"><fmt:message bundle="${i18n}" key="main.gotocat"/></a>
    </span>
    </div>

    <h2 class="n_p"><span><fmt:message bundle="${i18n}" key="main.newgoods"/></span></h2>
    <span class="line3"></span>

    <div class="new-product">
        <c:forEach var="product" items="${products}" varStatus="status" end="3">
            <div class="product-box">
                <img src="${product.image}">
                <h3><a href="/shop?page=product&id=${product.id}">${product.name}</a></h3>
                <p class="price"><fmt:formatNumber type="number" pattern="####.##" value="${product.price -(product.price*product.discount)}"/> р.</p>
                <a class="buy" href="/shop?page=product&id=${product.id}"><span>В корзину</span></a>
                <p class="new"><span>New</span></p>
            </div>
        </c:forEach>
    </div>

</div>
<!-- END of Main -->
