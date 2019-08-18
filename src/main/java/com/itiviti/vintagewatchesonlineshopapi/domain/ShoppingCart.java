package com.itiviti.vintagewatchesonlineshopapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingCart {

    @Id
    @NotNull
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customerShoppingCart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomerShoppingCart() {
        return customerShoppingCart;
    }

    public void setCustomerShoppingCart(Customer customerShoppingCart) {
        this.customerShoppingCart = customerShoppingCart;
    }
}