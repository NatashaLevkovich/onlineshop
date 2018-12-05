<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="product-table">
    <h1>Таблица товаров</h1>
    <a href="javascript:RedactorShow()">Редактировать</a>
    <table class="product-table">
        <tr>
            <th>ID</th>
            <th>Название товара</th>
            <th>Цена</th>
            <th>Скидка</th>
            <th>Категория</th>
            <th>Подкатегория</th>
            <th>Изображение</th>
            <th>Производитель</th>
            <th>Состав</th>
            <th>Размеры</th>
            <th>Количество</th>

        </tr>

        <c:forEach var="product" items="${products}">
            <tr>
                <th>${product.id}</th>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.discount}</td>
                <td>${product.category}</td>
                <td>${product.subcategory}</td>
                <td>${product.image}</td>
                <td>${product.manufacturer}</td>
                <td>${product.material}</td>
                <td>
                    <table>
                        <c:forEach var="size" items="${product.sizeAndQuantity}">
                            <td>${size.key}</td>
                        </c:forEach>
                    </table>
                </td>

                <td>
                    <table>
                        <c:forEach var="size" items="${product.sizeAndQuantity}">
                            <td>${size.value}</td>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>

        <form id="redactorproduct" action="/shop?page=admin&action=redactor" method="post">

            <tr class="redactor">
                <td><input type="text" name="id" required></td>
                <td><input type="text" name="name"></td>
                <td><input type="text" name="price"></td>
                <td><input type="text" name="discount"></td>
                <td><input type="text" list="cat1" name="category">
                    <datalist id="cat1">
                        <option>CLOTHES</option>
                        <option>TOYS</option>
                        <option>HYGIENE</option>
                    </datalist>
                </td>
                <td><input type="text" list="subcat1" name="subcategory">
                    <datalist id="subcat1">
                        <option>GIRLS</option>
                        <option>BOYS</option>
                        <option>NEWBORN</option>
                        <option>UNDERWEAR</option>
                    </datalist>
                </td>
                <td><input type="text" name="image"></td>
                <td><input type="text" name="manufacturer"></td>
                <td><input type="text" name="material"></td>
                <td><input type="text" name="size"></td>
                <td><input type="text" name="quantity"></td>
                <td>
                    <button form="redactorproduct">Редактировать</button>
                    <a href="javascript:RedactorHide()">Закрыть</a></td>
                </td>
            </tr>

        </form>

        <form id="setproduct" action="/shop?page=admin&action=set" method="post">
            <tr>
                <td></td>
                <td><input type="text" name="name" required></td>
                <td><input type="text" name="price" required></td>
                <td><input type="text" name="discount" required></td>
                <td><input type="text" list="cat" name="category" required>
                    <datalist id="cat">
                        <option>CLOTHES</option>
                        <option>TOYS</option>
                        <option>HYGIENE</option>
                    </datalist>
                </td>
                <td><input type="text" list="subcat" name="subcategory" required>
                    <datalist id="subcat">
                        <option>GIRLS</option>
                        <option>BOYS</option>
                        <option>NEWBORN</option>
                        <option>UNDERWEAR</option>
                    </datalist>
                </td>
                <td><input type="text" name="image"></td>
                <td><input type="text" name="manufacturer"></td>
                <td><input type="text" name="material"></td>
                <td><input type="text" name="size" required></td>
                <td><input type="text" name="quantity" required></td>
                <td>
                    <button form="setproduct">Добавить</button>
                </td>
            </tr>
        </form>

    </table>
</div>

<div class="order-table">
    <h1>Таблица заказов</h1>
    <a href="javascript:RedactorOrderShow()">Изменить статус</a>
    <table>
        <tr>
            <th>ID заказа</th>
            <th>Статус</th>
            <th>Дата заказа</th>
            <th>Сумма заказа</th>
            <th>ID клиента</th>
            <th>Ф.И.О.</th>
            <th>E-mail</th>
            <th>Адрес доставки</th>
            <th>Номер телефона</th>
            <th></th>
        </tr>

        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.status}</td>
            <td><fmt:formatDate value="${order.orderDate.time}" pattern="dd/MM/yyyy" /></td>
            <td>${order.totalPrice}</td>
            <td>${order.userId}</td>
            <c:forEach var="user" items="${users}">
                <c:if test="${user.id eq order.userId}">
                    <td>${user.firstName} ${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.address}</td>
                    <td>${user.phoneNumber}</td>
                    <td><a href="/download?file=order${order.id}.doc">Скачать заказ</a></td>
                </c:if>
            </c:forEach>
        </tr>
        </c:forEach>

        <form id="redactororder" action="/shop?page=admin&action=redactororder" method="post">

            <tr class="redactororder">
                <td><input type="text" name="id" required></td>
                <td><input type="text" list="status" name="status" required>
                    <datalist id="status">
                        <option>IS_FORMED</option>
                        <option>DELIVERED</option>
                    </datalist></td>

                <td>
                    <button form="redactororder">Редактировать</button>
                    <a href="javascript:RedactorOrderHide()">Закрыть</a></td>
                </td>
            </tr>

        </form>

    </table>

</div>

<div class="user-table">
    <h1>Таблица пользователей</h1>
    <a href="javascript:RedactorUserShow()">Редактировать</a>
    <table>
        <tr>
            <th>ID</th>
            <th>E-mail</th>
            <th>Пароль</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Адресс</th>
            <th>Номер телефона</th>
            <th>Скидка</th>
            <th>Статус</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.address}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.discount}</td>
                <td>${user.status}</td>
            </tr>
        </c:forEach>

        <form id="redactoruser" action="/shop?page=admin&action=redactoruser" method="post">

            <tr class="redactoruser">
                <td><input type="text" name="id" required></td>
                <td></td>
                <td></td>
                <td><input type="text" name="firstname"></td>
                <td><input type="text" name="lastname"></td>
                <td><input type="text" name="address"></td>
                <td><input type="text" name="phone"></td>
                <td><input type="text" name="discount"></td>
                <td><input type="text" name="status"></td>
                <td>
                    <button form="redactoruser">Редактировать</button>
                    <a href="javascript:RedactorUserHide()">Закрыть</a></td>
                </td>
            </tr>

        </form>

        <form id="setuser" action="/shop?page=admin&action=setuser" method="post">
            <tr>
                <td></td>
                <td><input type="text" name="email" required></td>
                <td><input type="text" name="password" required></td>
                <td><input type="text" name="firstname"></td>
                <td><input type="text" name="lastname"></td>
                <td><input type="text" name="address"></td>
                <td><input type="text" name="phone"></td>
                <td><input type="text" name="discount"></td>
                <td><input type="text" name="status"></td>
                <td> <button form="setuser">Добавить</button></td>
            </tr>
        </form>
    </table>
</div>