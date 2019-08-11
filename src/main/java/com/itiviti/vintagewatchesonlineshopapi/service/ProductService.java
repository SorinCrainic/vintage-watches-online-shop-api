package com.itiviti.vintagewatchesonlineshopapi.service;

import com.itiviti.vintagewatchesonlineshopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    //IoC (Inversion on Control) and DI (Dependency Injection)
//    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
