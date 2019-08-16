package com.itiviti.vintagewatchesonlineshopapi.web;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.FindProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //endpoint
    @GetMapping
    public ResponseEntity<Page<Product>> findProducts(FindProductRequest requestProductController, Pageable pageable){
        Page<Product> responseProductController = productService.findProducts(requestProductController, pageable);
        return new ResponseEntity<>(responseProductController, HttpStatus.OK);

    }
}
