package com.itiviti.vintagewatchesonlineshopapi;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.service.CustomerService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CreateCustomerRequest;
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
public class CustomerServiceIntegrationTests {

    //add CustomerService dependency (dependency injection)
    @Autowired
    private CustomerService customerServiceTest;

    //1.1 Test for method createCustomer: positive test (valid request)
    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {

        createCustomerTest();
    }

    private Customer createCustomerTest() {
        CreateCustomerRequest requestCreateCustomerPositiveTest = new CreateCustomerRequest();

        requestCreateCustomerPositiveTest.setCustomerFirstName("Sorin");
        requestCreateCustomerPositiveTest.setCustomerLastName("Crainic");
        requestCreateCustomerPositiveTest.setCustomerAge(32);
        requestCreateCustomerPositiveTest.setCustomerAddress("Dunarii 144");

        Customer createdCustomerPositiveTest = customerServiceTest.createCustomer(requestCreateCustomerPositiveTest);

        assertThat(createdCustomerPositiveTest, notNullValue());
        assertThat(createdCustomerPositiveTest.getId(), greaterThan(0L));
        assertThat(createdCustomerPositiveTest.getCustomerFirstName(), is(requestCreateCustomerPositiveTest.getCustomerFirstName()));
        assertThat(createdCustomerPositiveTest.getCustomerLastName(), is(requestCreateCustomerPositiveTest.getCustomerLastName()));
        assertThat(createdCustomerPositiveTest.getCustomerAge(), is(requestCreateCustomerPositiveTest.getCustomerAge()));
        assertThat(createdCustomerPositiveTest.getCustomerAddress(), is(requestCreateCustomerPositiveTest.getCustomerAddress()));

        return createdCustomerPositiveTest;
    }
}