<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="main">
<c:set var="productinfo" value="${productinfo}"/>
    <c:set var="notUser" value="${notUser}" scope="session"/>


    <img src="${productinfo.image}">
    <p>${productinfo.name}</p>
    <p>Цена: <fmt:formatNumber type="number" pattern="####.##" value="${productinfo.price -(productinfo.price*productinfo.discount)}"/> р.</p>
    <p>Размер:
        <form id="add-product" action="/shop?page=item&id=${productinfo.id}" method="post">
    <c:forEach var="size" items="${productinfo.sizeAndQuantity}" >
       <input type="radio" value="${size.key}" name="size" required>${size.key}
    </c:forEach>
    </p>
    <p>Выберите количество: </p>
    <c:set var="error" value="${error}" scope="session"/>
    <c:if test="${not empty error}">
        ${error}
    </c:if>
    <% session.removeAttribute("error"); %>
    <input type="number" min="1" step="1" name="quantity" required>
    <button form="add-product">В корзину</button>
    </form>
    <p>Состав: ${productinfo.material}</p>
    <p>Производитель: ${productinfo.manufacturer}</p>

    <c:if test="${notUser == true}">
        <script>
            $(document).ready(function () {
               LoginShow();
                });
        </script>
    </c:if>
    <% session.removeAttribute("notUser"); %>

</div><!-- END of main -->
