package entities;

import java.util.Calendar;
import java.util.List;

public class Order {
    private long id;
    private long userId;
    private List<Item> items;
    private double totalPrice;
    private Enum status;
    private Calendar orderDate;

    public Order() {

    }

    public Order(long userId, Enum status, Calendar orderDate) {
        this.userId = userId;
        this.status = status;
        this.orderDate = orderDate;


    }

    public Order(long userId, List<Item> items, double totalPrice, Enum status, Calendar orderDate) {
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;

    }

    public Order(long id, long userId, List<Item> items, double totalPrice, Enum status, Calendar orderDate) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public String getOrderDateToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderDate.get(1)).append("-").append((orderDate.get(2)+1)).append("-").append(orderDate.get(5));
        return sb.toString();
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }
}
