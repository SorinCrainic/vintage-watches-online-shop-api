package com.itiviti.vintagewatchesonlineshopapi;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.service.ShoppingCartService;
import com.itiviti.vintagewatchesonlineshopapi.steps.CustomerSteps;
import com.itiviti.vintagewatchesonlineshopapi.steps.ProductSteps;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.AddProductToShoppingCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceIntegrationTests {

    @Autowired
    private ShoppingCartService shoppingCartServiceTest;

    @Autowired
    private CustomerSteps customerStepsTest;

    @Autowired
    private ProductSteps productStepsTest;

    //1.1 Test for method addProductToShoppingCart: positive test (valid request)
    @Test
    public void testAddProductToShoppingCart_whenValidRequest_thenReturnAddedProduct() throws NotFoundException {
        Customer customerShoppingCartTest = customerStepsTest.createCustomerTest();
        Product productShoppingCartTest = productStepsTest.createProductTest();

        AddProductToShoppingCartRequest requestAddShoppingCartTest = new AddProductToShoppingCartRequest();
        requestAddShoppingCartTest.setCustomerId(customerShoppingCartTest.getId());
        requestAddShoppingCartTest.setProdcutId(productShoppingCartTest.getId());

        shoppingCartServiceTest.addProductToShoppingCart(requestAddShoppingCartTest);
    }
}