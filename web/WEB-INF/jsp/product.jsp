<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="main-product">
<c:set var="productinfo" value="${productinfo}"/>
    <c:set var="notUser" value="${notUser}" scope="session"/>


    <h3>${productinfo.name}</h3>
    <img src="${productinfo.image}">
    <p>Цена: <fmt:formatNumber type="number" pattern="####.##" value="${productinfo.price -(productinfo.price*productinfo.discount)}"/> р.</p>
    <p>Размер:</p>
        <form id="add-product" action="${pageContext.request.contextPath}/shop?page=item&id=${productinfo.id}" method="post">
    <c:forEach var="size" items="${productinfo.sizeAndQuantity}" >
       <p><input type="radio" value="${size.key}" name="size" required>${size.key}</p>
    </c:forEach>

    <p>Выберите количество: </p>
    <c:set var="error" value="${error}" scope="session"/>
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>
    <% session.removeAttribute("error"); %>
    <p><input type="number" min="1" step="1" name="quantity" required></p>
    <p><button form="add-product">В корзину</button></p>
    </form>

    <div><p>Описание</p>
    <p>Состав: ${productinfo.material}</p>
    <p>Производитель: ${productinfo.manufacturer}</p>
    </div>

    <c:if test="${notUser == true}">
        <script>
            $(document).ready(function () {
               LoginShow();
                });
        </script>
    </c:if>
    <% session.removeAttribute("notUser"); %>

</div><!-- END of main -->
