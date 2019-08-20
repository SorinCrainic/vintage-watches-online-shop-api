package com.itiviti.vintagewatchesonlineshopapi.steps;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.domain.ProductReview;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductReviewService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.productReview.CreateProductReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class ProductReviewSteps {

    //add ProductReviewService dependency (Dependency Injection)
    @Autowired
    private ProductReviewService productReviewServiceTest;

    public ProductReview createProductReviewTest() {
        CreateProductReviewRequest requestCreateProductReviewPositiveTest = new CreateProductReviewRequest();
        Product productCreateProductPositiveTes = new Product();

        requestCreateProductReviewPositiveTest.setProduct(productCreateProductPositiveTes);
        requestCreateProductReviewPositiveTest.setReviewContent("Vintage automatic watch, mint condition, collectible.");

        ProductReview createdProductReviewPositiveTest = productReviewServiceTest.createProductReview(requestCreateProductReviewPositiveTest);

        assertThat(createdProductReviewPositiveTest, notNullValue());
        assertThat(createdProductReviewPositiveTest.getId(), greaterThan(0L));
        assertThat(createdProductReviewPositiveTest.getProduct(), is(requestCreateProductReviewPositiveTest.getProduct()));
        assertThat(createdProductReviewPositiveTest.getReviewContent(), is(requestCreateProductReviewPositiveTest.getReviewContent()));

        return createdProductReviewPositiveTest;
    }
}
