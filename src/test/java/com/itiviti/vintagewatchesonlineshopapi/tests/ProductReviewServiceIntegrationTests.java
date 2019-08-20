package com.itiviti.vintagewatchesonlineshopapi.tests;

import com.itiviti.vintagewatchesonlineshopapi.service.ProductReviewService;
import com.itiviti.vintagewatchesonlineshopapi.steps.ProductReviewSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductReviewServiceIntegrationTests {

    //add ProductReviewService dependency (DI)
    @Autowired
    private ProductReviewService productReviewServiceTest;

    @Autowired
    private ProductReviewSteps productReviewStepsTest;

    //1.1 Test for method createProductReview: positive test (valid request)
    @Test
    public void testCreateProductReview_whenValidRequest_thenReturnCreatedProductReview() {
        productReviewStepsTest.createProductReviewTest();
    }
}
