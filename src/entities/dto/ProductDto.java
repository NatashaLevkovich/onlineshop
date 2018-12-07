package entities.dto;

public class ProductDto {
    private String name;
    private double price;
    private double discount;
    private String image;
    private String manufacturer;
    private String material;
    private int size;
    private int quantity;
    private long itemId;


    public ProductDto() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public ProductDto(String name, double price, double discount, String image, String manufacturer, String material, int size, int quantity, long itemId) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.manufacturer = manufacturer;
        this.material = material;
        this.size = size;
        this.quantity = quantity;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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

    @Override
    public String toString() {
        return "Название: " + name +
                ", цена: " + price +
                ", скидка: " + discount +
                ", размер: " + size +
                ", количество: " + quantity;
    }
}
