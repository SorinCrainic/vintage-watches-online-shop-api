package com.itiviti.vintagewatchesonlineshopapi.web;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.service.CustomerService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //endpoint: POST (add/create) new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomerCustomerController(@RequestBody @Valid CreateCustomerRequest requestCreateCustomerController) {
        Customer createdCustomerCustomerController = customerService.createCustomer(requestCreateCustomerController);
        return new ResponseEntity<>(createdCustomerCustomerController, HttpStatus.CREATED);
    }
}
