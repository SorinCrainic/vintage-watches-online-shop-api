package com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart;

//DTO: data transfer object
public class AddProductToShoppingCartRequest {

    private Long customerId;

    private Long prodcutId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(Long prodcutId) {
        this.prodcutId = prodcutId;
    }

    @Override
    public String toString() {
        return "AddProductToShoppingCartRequest{" +
                "customerId=" + customerId +
                ", prodcutId=" + prodcutId +
                '}';
    }
}
