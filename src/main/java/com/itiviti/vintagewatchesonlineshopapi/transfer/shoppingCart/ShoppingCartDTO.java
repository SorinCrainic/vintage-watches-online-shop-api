package com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart;

import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CustomerDTO;

public class ShoppingCartDTO {

    private Long id;
    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}
