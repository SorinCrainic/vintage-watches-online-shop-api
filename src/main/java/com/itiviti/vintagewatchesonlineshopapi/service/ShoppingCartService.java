package com.itiviti.vintagewatchesonlineshopapi.service;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.domain.ShoppingCart;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.repository.ShoppingCartRepository;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.AddProductToShoppingCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartService.class);

    //Inversion of Control (IoC)
    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerService customerService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CustomerService customerService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerService = customerService;
    }

    //Method for ADDING a Product to a ShoppingCart
    @Transactional
    public void addProductToShoppindCart(AddProductToShoppingCartRequest requestAddProductToShoppingCart) throws NotFoundException {
        LOGGER.info("Adding product " + requestAddProductToShoppingCart + " to shopping cart.");

        //retrieve from db the existing customer which will be assigned to the shopping cart (the customer must already be created/exist into db)
        Customer customerAssignedToShoppingCart = customerService.getCustomer(requestAddProductToShoppingCart.getCustomerId());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerShoppingCart(customerAssignedToShoppingCart);

        shoppingCartRepository.save(shoppingCart);
    }
}