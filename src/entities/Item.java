package entities;

public class Item {
    private long id;
    private long productId;
    private int productSize;
    private long orderId;
    private int quantity;
    private double discount;


    public Item() {
    }

    public Item(long productId, int productSize, long orderId, int quantity, double discount) {
        this.productId = productId;
        this.productSize = productSize;
        this.orderId = orderId;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Item(long id, long productId, int productSize, long orderId, int quantity, double discount) {
        this.id = id;
        this.productId = productId;
        this.productSize = productSize;
        this.orderId = orderId;
        this.quantity = quantity;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }
}
