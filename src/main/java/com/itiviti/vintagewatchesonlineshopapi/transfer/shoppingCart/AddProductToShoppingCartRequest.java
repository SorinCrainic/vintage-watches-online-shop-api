package com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart;

import javax.validation.constraints.NotNull;

//DTO: data transfer object
public class AddProductToShoppingCartRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long productId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProdcutId() {
        return productId;
    }

    public void setProdcutId(Long prodcutId) {
        this.productId = prodcutId;
    }

    @Override
    public String toString() {
        return "AddProductToShoppingCartRequest{" +
                "customerId=" + customerId +
                ", prodcutId=" + productId +
                '}';
    }
}
