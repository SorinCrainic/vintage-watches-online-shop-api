package com.itiviti.vintagewatchesonlineshopapi.tests;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.repository.ProductRepository;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.steps.ProductSteps;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.CreateProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.UpdateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    //add ProductRepository dependency (DI)
    @Autowired
    public ProductRepository productRepository;

    //add ProductService dependency (DI)
    @Autowired
    private ProductService productServiceTest;

    @Autowired
    private ProductSteps productStepsTest;

    //1.1 Test for method createProduct: positive test (valid request)
    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {
        productStepsTest.createProductTest();
    }

    //1.2 Test for method createProduct: negative test (not valid request -> mandatory fields are not populated (in fact, none of the fields are populated in this case))
    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenNotValidRequest_thenThrowException() {

        CreateProductRequest requestNegativeTest = new CreateProductRequest();
        productServiceTest.createProduct(requestNegativeTest);
    }

    //2.1 Test for method getProduct: positive test (valid request)
    @Test
    public void testGetProduct_whenValidRequest_thenReturnRetrievedProduct() throws NotFoundException {
        Product createdProductTest = productStepsTest.createProductTest();
        Product retrievedProduct = productServiceTest.getProduct(createdProductTest.getId());
        assertThat(retrievedProduct, notNullValue());
        assertThat(retrievedProduct.getId(), is(createdProductTest.getId()));
        productServiceTest.deleteProduct(createdProductTest.getId());
    }

    //2.2 Test for method getProduct: negative test (not valid request)
    @Test(expected = NotFoundException.class)
    public void testGetProduct_whenNonValidRequest_thenThrowException() throws NotFoundException {
        productServiceTest.getProduct(1250L);
    }

    //3.1 Test for method updateProduct: positive test (valid request)
    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() throws NotFoundException {
        Product createdProductTestUpdate = productStepsTest.createProductTest();
        UpdateProductRequest requestUpdateProduct = new UpdateProductRequest();

        requestUpdateProduct.setName(createdProductTestUpdate.getName() + " updated");
        requestUpdateProduct.setPrice(createdProductTestUpdate.getPrice() + 10);
        requestUpdateProduct.setQuantity(createdProductTestUpdate.getQuantity() + 1);
        requestUpdateProduct.setImagePath(createdProductTestUpdate.getImagePath());

        Product updatedProduct = productServiceTest.updateProduct(createdProductTestUpdate.getId(), requestUpdateProduct);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(createdProductTestUpdate.getId()));

        assertThat(updatedProduct.getName(), not(is(createdProductTestUpdate.getName())));
        assertThat(updatedProduct.getName(), is(requestUpdateProduct.getName()));

        assertThat(updatedProduct.getPrice(), not(is(createdProductTestUpdate.getPrice())));
        assertThat(updatedProduct.getPrice(), is(requestUpdateProduct.getPrice()));

        assertThat(updatedProduct.getQuantity(), not(is(createdProductTestUpdate.getQuantity())));
        assertThat(updatedProduct.getQuantity(), is(requestUpdateProduct.getQuantity()));
    }

//    //3.2 Test for method updateProduct: negative test (not valid request)
//    @Test(expected = AssertionError.class)
//    public void testUpdateProduct_whenNotValidRequest_thenThrowException() throws NotFoundException {
//        Product createdProduct_UpdateProductNegativeTest = productStepsTest.createProductTest();
//        UpdateProductRequest request_UpdateProductNegativeTest = new UpdateProductRequest();
//
//        request_UpdateProductNegativeTest.setName(createdProduct_UpdateProductNegativeTest.getName() + " updated - for negative test");
//        request_UpdateProductNegativeTest.setPrice(createdProduct_UpdateProductNegativeTest.getPrice() + 100);
//        request_UpdateProductNegativeTest.setQuantity(createdProduct_UpdateProductNegativeTest.getQuantity() + 5);
//
//        Product updatedProduct_UpdateProductNegativeTest = productServiceTest.updateProduct(createdProduct_UpdateProductNegativeTest.getId(), request_UpdateProductNegativeTest);
//
//        assertThat(updatedProduct_UpdateProductNegativeTest, notNullValue());
//        assertThat(updatedProduct_UpdateProductNegativeTest.getId(), is(createdProduct_UpdateProductNegativeTest.getId()));
//
//        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), notNullValue());
//        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), not(is(createdProduct_UpdateProductNegativeTest.getName())));
//        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), not(is(request_UpdateProductNegativeTest.getName())));
//
//        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), notNullValue());
//        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), not(is(createdProduct_UpdateProductNegativeTest.getPrice())));
//        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), not(is(request_UpdateProductNegativeTest.getPrice())));
//
//        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), notNullValue());
//        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), not(is(createdProduct_UpdateProductNegativeTest.getQuantity())));
//        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), not(is(request_UpdateProductNegativeTest.getQuantity())));
//
//        productServiceTest.deleteProduct(createdProduct_UpdateProductNegativeTest.getId());
//        productServiceTest.deleteProduct(updatedProduct_UpdateProductNegativeTest.getId());
//        productRepository.deleteById(createdProduct_UpdateProductNegativeTest.getId());
//        productRepository.deleteById(updatedProduct_UpdateProductNegativeTest.getId());
//    }

    //4.1 Test for method deleteProduct: positive test (valid request)
    @Test
    public void testDeleteProduct_whenValidRequest() {
        Product createdProduct_DeleteProductTest = productStepsTest.createProductTest();

//        productServiceTest.deleteProduct(createdProduct_DeleteProductTest.getId());

//        productRepository.deleteById(createdProduct_DeleteProductTest.getId());
        productServiceTest.deleteProduct(createdProduct_DeleteProductTest.getId());


        assertThat(createdProduct_DeleteProductTest.getId(), is(nullValue()));
    }

    //4.2 Test for method deleteProduct: negative test (not valid request)
    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteProduct_whenNotValidRequest_thenThrowException() throws EmptyResultDataAccessException {
        productServiceTest.deleteProduct(1250);
    }
}