<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="main">

    <p><a href="${pageContext.request.contextPath}/shop?page=main">Главная</a> \ <a href="${pageContext.request.contextPath}/shop?page=catalog">Каталог</a> </p>

    <div class="left-menu">
        <h3>Категории</h3>
        <ul>
            <li><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=boys">Одежда для мальчиков</a></li>
            <li><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=girls">Одежда для девочек</a></li>
            <li><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=newborn">Одежда для новороденных</a></li>
            <%--<li><a href="${pageContext.request.contextPath}/shop?page=catalog&cat=underwear">Нижнее белье</a></li>--%>
        </ul>
    </div>


    <div class="main-header">
        <p>Сортировать по:</p>
        <select>
            <option>цене</option>
            <option>названию</option>
        </select>
        <p>Показывать по:</p>
        <ul>
            <li><a href="#">10</a></li>
            <li><a href="#">30</a></li>
            <li><a href="#">50</a></li>
        </ul>
    </div>

    <div class="product">

        <c:forEach var="product" items="${products}">

            <div class="product-box">
                <img src="${product.image}">
                <h3><a href="${pageContext.request.contextPath}/shop?page=product&id=${product.id}">${product.name}</a></h3>
                <p class="price"><fmt:formatNumber type="number" pattern="####.##" value="${product.price -(product.price*product.discount)}"/> р.</p>
                <a class="buy" href="${pageContext.request.contextPath}/shop?page=product&id=${product.id}"><span>Купить</span></a>
            </div>
        </c:forEach>

    </div>

    <div class="pagination">
        <ul>
            <li><a href="#">Предыдущая</a></li>
            <li><a href="#1">1</a></li>
            <li><a href="#2">2</a></li>
            <li><a href="#3">3</a></li>
            <li><a href="#">Следующая</a></li>
        </ul>
    </div>

</div> <!-- END of main -->

