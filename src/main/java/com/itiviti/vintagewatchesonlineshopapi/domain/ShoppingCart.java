package com.itiviti.vintagewatchesonlineshopapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @NotNull
    private Long id;

    //OneToOne (ShoppingCart to Customer; ShoppingCart is the owner of the relationship)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customerShoppingCart;

    //ManyToMany (ShoppingCart to Product; ShoppingCart is the owner of the relationship)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name ="shopping_cart_product", joinColumns = @JoinColumn(name="shopping_cart_id"), inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products = new HashSet<>();

    //Method for ADDING a product to the current shopping cart
    public void addProductToCurrentShoppingCart(Product productToBeAddedToShoppingCart){
        //adding product to current shopping cart
        products.add(productToBeAddedToShoppingCart);

        // adding current shopping cart to the carts collection of the received product
        productToBeAddedToShoppingCart.getShoppingCart().add(this);
    }

    //Method for REMOVING a product from the current shopping cart
    public void removeProductFromCurrentShoppingCart(Product productToBeRemovedFromShoppingCart){
        products.remove(productToBeRemovedFromShoppingCart);
        productToBeRemovedFromShoppingCart.getShoppingCart().remove(this);
    }

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}