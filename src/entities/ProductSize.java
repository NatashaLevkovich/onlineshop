package entities;

public class ProductSize {
    private long id;
    private long productId;
    private int size;
    private int quantity;


    public ProductSize() {
    }

    public ProductSize(long productId, int size, int quantity) {
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
    }

    public ProductSize(long id, long productId, int size, int quantity) {
        this.id = id;
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
