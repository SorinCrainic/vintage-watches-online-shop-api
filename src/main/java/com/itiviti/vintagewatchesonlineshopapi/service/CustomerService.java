package com.itiviti.vintagewatchesonlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.repository.CustomerRepository;
import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CreateCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    //IoC (Inversion on Control) and DI (Dependency Injection)
    @Autowired
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    //Method for CREATING a customer (Crud)
    public Customer createCustomer(CreateCustomerRequest requestCreateCustomer) {

        LOGGER.info("Creating customer {}", requestCreateCustomer);

//        Customer newCreatedCustomer = new Customer();
//        newCreatedCustomer.setCustomerFirstName(requestCreateCustomer.getCustomerFirstName());
//        newCreatedCustomer.setCustomerLastName(requestCreateCustomer.getCustomerLastName());
//        newCreatedCustomer.setCustomerAge(requestCreateCustomer.getCustomerAge());
//        newCreatedCustomer.setCustomerAddress(requestCreateCustomer.getCustomerAddress());

        Customer newCreatedCustomer = objectMapper.convertValue(requestCreateCustomer, Customer.class);

        return customerRepository.save(newCreatedCustomer);
    }

    //Method for RETRIEVING a customer from db by productId (cRud)
    public Customer getCustomer(long id) throws NotFoundException {
        LOGGER.info("Retrieving customer {}", id);
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer" + id + "not exist."));
    }
}