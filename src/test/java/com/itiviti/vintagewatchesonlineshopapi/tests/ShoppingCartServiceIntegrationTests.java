package com.itiviti.vintagewatchesonlineshopapi.tests;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.service.ShoppingCartService;
import com.itiviti.vintagewatchesonlineshopapi.steps.CustomerSteps;
import com.itiviti.vintagewatchesonlineshopapi.steps.ProductSteps;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.ProductDTO;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.AddProductToShoppingCartRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.ShoppingCartDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

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

        ShoppingCartDTO productRetrievedFromShoppingCartTest = shoppingCartServiceTest.getProductFromShoppingCart(customerShoppingCartTest.getId());

        assertThat(productRetrievedFromShoppingCartTest, notNullValue());
        assertThat(productRetrievedFromShoppingCartTest.getCustomer(), notNullValue());
        assertThat(productRetrievedFromShoppingCartTest.getCustomer().getId(), is(customerShoppingCartTest.getId()));
        assertThat(productRetrievedFromShoppingCartTest.getCustomer().getCustomerFirstName(), is(customerShoppingCartTest.getCustomerFirstName()));
        assertThat(productRetrievedFromShoppingCartTest.getCustomer().getCustomerLastName(), is(customerShoppingCartTest.getCustomerLastName()));
        assertThat(productRetrievedFromShoppingCartTest.getCustomer().getCustomerAddress(), is(customerShoppingCartTest.getCustomerAddress()));

        assertThat(productRetrievedFromShoppingCartTest.getProducts(), notNullValue());
        assertThat(productRetrievedFromShoppingCartTest.getProducts(), hasSize(1));

        ProductDTO firstProductFromShoppingChart = productRetrievedFromShoppingCartTest.getProducts().iterator().next();

        assertThat(firstProductFromShoppingChart, notNullValue());
        assertThat(firstProductFromShoppingChart.getName(), is(productShoppingCartTest.getName()));
        assertThat(firstProductFromShoppingChart.getQuantity(), is(productShoppingCartTest.getQuantity()));
        assertThat(firstProductFromShoppingChart.getPrice(), is(productShoppingCartTest.getPrice()));
        assertThat(firstProductFromShoppingChart.getImagePath(), is(productShoppingCartTest.getImagePath()));
        assertThat(firstProductFromShoppingChart.getProductDescription(), is(productShoppingCartTest.getProductDescription()));
        assertThat(firstProductFromShoppingChart.getProductRate(), is(productShoppingCartTest.getProductRate()));
    }
}