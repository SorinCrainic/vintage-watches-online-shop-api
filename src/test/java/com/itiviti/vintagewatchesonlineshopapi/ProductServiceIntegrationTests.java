package com.itiviti.vintagewatchesonlineshopapi;

        import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
        import com.itiviti.vintagewatchesonlineshopapi.exceptions.ProductNotFoundException;
        import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
        import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
        import com.itiviti.vintagewatchesonlineshopapi.transfer.UpdateProductRequest;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.transaction.TransactionSystemException;

        import javax.validation.ConstraintViolationException;

        import static org.hamcrest.CoreMatchers.*;
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

    //1.2 Test for method createProduct: negative test (not valid request -> missing mandatory parameter (Id in this case), for example)
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

    //2.2 Test for method getProduct: negative test (not valid request)
    @Test(expected = ProductNotFoundException.class)
    public void testGetProduct_whenNonValidRequest_thenThrowException() throws ProductNotFoundException {
        productServiceTest.getProduct(1250L);
    }

    //3.1 Test for method updateProduct: positive test (valid request)
    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() throws ProductNotFoundException {
        Product createdProductTestUpdate = createProductTest();
        UpdateProductRequest requestUpdateProduct = new UpdateProductRequest();

        requestUpdateProduct.setName(createdProductTestUpdate.getName() + " updated");
        requestUpdateProduct.setPrice(createdProductTestUpdate.getPrice() + 10);
        requestUpdateProduct.setQuantity(createdProductTestUpdate.getQuantity() + 1);

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

    //3.2 Test for method updateProduct: negative test (not valid request)
    @Test(expected = AssertionError.class)
    public void testUpdateProduct_whenNotValidRequest_thenThrowException() throws ProductNotFoundException {
        Product createdProduct_UpdateProductNegativeTest = createProductTest();
        UpdateProductRequest request_UpdateProductNegativeTest = new UpdateProductRequest();

        request_UpdateProductNegativeTest.setName(createdProduct_UpdateProductNegativeTest.getName() + " updated - for negative test");
        request_UpdateProductNegativeTest.setPrice(createdProduct_UpdateProductNegativeTest.getPrice() + 100);
        request_UpdateProductNegativeTest.setQuantity(createdProduct_UpdateProductNegativeTest.getQuantity() + 5);

        Product updatedProduct_UpdateProductNegativeTest = productServiceTest.updateProduct(createdProduct_UpdateProductNegativeTest.getId(), request_UpdateProductNegativeTest);

        assertThat(updatedProduct_UpdateProductNegativeTest, notNullValue());
        assertThat(updatedProduct_UpdateProductNegativeTest.getId(), is(createdProduct_UpdateProductNegativeTest.getId()));

        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), notNullValue());
        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), not(is(createdProduct_UpdateProductNegativeTest.getName())));
        assertThat(updatedProduct_UpdateProductNegativeTest.getName(), not(is(request_UpdateProductNegativeTest.getName())));

        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), notNullValue());
        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), not(is(createdProduct_UpdateProductNegativeTest.getPrice())));
        assertThat(updatedProduct_UpdateProductNegativeTest.getPrice(), not(is(request_UpdateProductNegativeTest.getPrice())));

        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), notNullValue());
        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), not(is(createdProduct_UpdateProductNegativeTest.getQuantity())));
        assertThat(updatedProduct_UpdateProductNegativeTest.getQuantity(), not(is(request_UpdateProductNegativeTest.getQuantity())));
    }
}