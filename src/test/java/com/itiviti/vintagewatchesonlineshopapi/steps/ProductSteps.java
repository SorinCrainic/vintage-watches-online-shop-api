package com.itiviti.vintagewatchesonlineshopapi.steps;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class ProductSteps {

    //add ProductService dependency
    @Autowired
    private ProductService productServiceTest;

    public Product createProductTest() {
        CreateProductRequest requestPositiveTest = new CreateProductRequest();
        requestPositiveTest.setName("Seiko Lord Matic");
        requestPositiveTest.setPrice(1000.00);
        requestPositiveTest.setQuantity(1);
        requestPositiveTest.setProductRate(10.00);
        requestPositiveTest.setProductDescription("Vintage automatic watch, mint condition, collectible.");

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
}
