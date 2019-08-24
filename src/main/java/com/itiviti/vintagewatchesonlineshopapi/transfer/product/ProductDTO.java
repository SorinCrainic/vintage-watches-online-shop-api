package com.itiviti.vintagewatchesonlineshopapi.transfer.product;

import java.util.Objects;

public class ProductDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private String imagePath;
    private String productDescription;
    private double productRate;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productRate=" + productRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id &&
                Double.compare(that.productRate, productRate) == 0 &&
                name.equals(that.name) &&
                quantity.equals(that.quantity) &&
                price.equals(that.price) &&
                Objects.equals(imagePath, that.imagePath) &&
                Objects.equals(productDescription, that.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, imagePath, productDescription, productRate);
    }
}
