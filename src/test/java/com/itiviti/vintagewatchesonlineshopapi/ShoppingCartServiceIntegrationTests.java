package com.itiviti.vintagewatchesonlineshopapi;

import com.itiviti.vintagewatchesonlineshopapi.service.ShoppingCartService;
import com.itiviti.vintagewatchesonlineshopapi.steps.CustomerSteps;
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

    //1.1 Test for method addProductToShoppingCart: positive test (valid request)
    @Test
    public void testAddProductToShoppingCart_whenValidRequest_thenReturnAddedProduct() {
        customerStepsTest.createCustomerTest();
    }
}