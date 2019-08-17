package com.itiviti.vintagewatchesonlineshopapi.transfer.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateProductRequest {

    @NotNull
    public String name;
    @Min(0)
    private Integer quantity;
    @NotNull
    private Double price;
    private String imagePath;
    private String productDescription;
    @NotNull
    @Min(0)
    @Max(10)
    private double productRate;

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
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productRate=" + productRate +
                '}';
    }
}
