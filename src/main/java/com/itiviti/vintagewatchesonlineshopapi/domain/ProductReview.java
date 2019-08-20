package com.itiviti.vintagewatchesonlineshopapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
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
        return "ProductReview{" +
                "id=" + id +
                ", reviewContent='" + reviewContent + '\'' +
                ", product=" + product +
                '}';
    }
}
