package com.itiviti.vintagewatchesonlineshopapi;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    //Test for method createProduct: positive test (valid request)
    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {

        CreateProductRequest requestTest = new CreateProductRequest();
        requestTest.setName("Ruhla");
        requestTest.setPrice(399.00);
        requestTest.setQuantity(1);
        requestTest.setProductRate(8);
        requestTest.setProductDescription("Mechanical chronograph (tachymeter).");

        Product createdProductTest = productServiceTest.createProduct(requestTest);

        assertThat(createdProductTest, notNullValue());
        assertThat(createdProductTest.getId(), greaterThan(0L));
        assertThat(createdProductTest.getName(), is(requestTest.getName()));
        assertThat(createdProductTest.getPrice(), is(requestTest.getPrice()));
        assertThat(createdProductTest.getQuantity(), is(requestTest.getQuantity()));
        assertThat(createdProductTest.getProductRate(),is(requestTest.getProductRate()));
        assertThat(createdProductTest.getProductDescription(),is(requestTest.getProductDescription()));
    }
}