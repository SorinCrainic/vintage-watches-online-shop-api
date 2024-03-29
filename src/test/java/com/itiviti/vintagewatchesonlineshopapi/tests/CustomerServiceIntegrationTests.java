package com.itiviti.vintagewatchesonlineshopapi.tests;

import com.itiviti.vintagewatchesonlineshopapi.steps.CustomerSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerSteps customerStepsTest;

    //1.1 Test for method createCustomer: positive test (valid request)
    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {
        customerStepsTest.createCustomerTest();
    }
}