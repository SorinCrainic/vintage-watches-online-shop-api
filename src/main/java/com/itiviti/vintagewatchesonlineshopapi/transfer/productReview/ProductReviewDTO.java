package com.itiviti.vintagewatchesonlineshopapi.transfer.productReview;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;

public class ProductReviewDTO {

    private Long id;
    private String reviewContent;
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductReviewDTO{" +
                "id=" + id +
                ", reviewContent='" + reviewContent + '\'' +
                ", product=" + product +
                '}';
    }
}