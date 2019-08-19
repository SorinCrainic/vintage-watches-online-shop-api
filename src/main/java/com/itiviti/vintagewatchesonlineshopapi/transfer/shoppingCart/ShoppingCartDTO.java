package com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart;

import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CustomerDTO;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.ProductDTO;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartDTO {

    private Long id;
    private CustomerDTO customer;
    private Set<ProductDTO> products = new HashSet<>();

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

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}
