package com.itiviti.vintagewatchesonlineshopapi;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.ProductNotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    //add ProductService dependency

    @Autowired
    private ProductService productServiceTest;

    //1.1 Test for method createProduct: positive test (valid request)
    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {

        createProductTest();
    }

    private Product createProductTest() {
        CreateProductRequest requestPositiveTest = new CreateProductRequest();
        requestPositiveTest.setName("Ruhla");
        requestPositiveTest.setPrice(399.00);
        requestPositiveTest.setQuantity(1);
        requestPositiveTest.setProductRate(8);
        requestPositiveTest.setProductDescription("Mechanical chronograph (tachymeter).");

        Product createdProductTest = productServiceTest.createProduct(requestPositiveTest);

        assertThat(createdProductTest, notNullValue());
        assertThat(createdProductTest.getId(), greaterThan(0L));
        assertThat(createdProductTest.getName(), is(requestPositiveTest.getName()));
        assertThat(createdProductTest.getPrice(), is(requestPositiveTest.getPrice()));
        assertThat(createdProductTest.getQuantity(), is(requestPositiveTest.getQuantity()));
        assertThat(createdProductTest.getProductRate(), is(requestPositiveTest.getProductRate()));
        assertThat(createdProductTest.getProductDescription(), is(requestPositiveTest.getProductDescription()));

        return createdProductTest;
    }

    //1.2 Test for method createProduct: negative test (not valid request -> missing mandatory parameter, for example)
    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenNotValidRequest_thenThrowException() {

        CreateProductRequest requestNegativeTest = new CreateProductRequest();
        productServiceTest.createProduct(requestNegativeTest);
    }

    //2.1 Test for method getProduct: positive test (valid request)
    @Test
    public void testGetProduct_whenValidRequest_thenReturnRetrievedProduct() throws ProductNotFoundException {
        Product createdProductTest = createProductTest();
        Product retrievedProduct = productServiceTest.getProduct(createdProductTest.getId());
        assertThat(retrievedProduct, notNullValue());
        assertThat(retrievedProduct.getId(), is(createdProductTest.getId()));
    }
}