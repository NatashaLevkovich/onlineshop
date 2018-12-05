package entities;

import java.util.Map;

public class Product {
    private long id;
    private String name;
    private double price;
    private double discount;
    private Enum category;
    private Enum subcategory;
    private String image;
    private String manufacturer;
    private String material;
    private Map<Integer, Integer> sizeAndQuantity;

    public Product() {
    }

    public Product(String name, double price, double discount, Enum category, Enum subcategory, String image, String manufacturer, String material) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.subcategory = subcategory;
        this.image = image;
        this.manufacturer = manufacturer;
        this.material = material;
    }

    public Product(long id, String name, double price, double discount, Enum category, Enum subcategory, String image, String manufacturer, String material) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.subcategory = subcategory;
        this.image = image;
        this.manufacturer = manufacturer;
        this.material = material;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Enum category) {
        this.category = category;
    }

    public Enum getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Enum subcategory) {
        this.subcategory = subcategory;
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

    public Map<Integer, Integer> getSizeAndQuantity() {
        return sizeAndQuantity;
    }

    public void setSizeAndQuantity(Map<Integer, Integer> sizeAndQuantity) {
        this.sizeAndQuantity = sizeAndQuantity;
    }
}
