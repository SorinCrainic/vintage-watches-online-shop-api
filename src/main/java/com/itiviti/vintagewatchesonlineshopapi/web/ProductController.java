package com.itiviti.vintagewatchesonlineshopapi.web;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.FindProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //endpoint: GET (retrieve existing products)
    @GetMapping
    public ResponseEntity<Page<Product>> findProducts(FindProductRequest requestGetProductController, Pageable pageable){
        Page<Product> responseProductController = productService.findProducts(requestGetProductController, pageable);
        return new ResponseEntity<>(responseProductController, HttpStatus.OK);
    }

    //endpoint: create new product
    @PostMapping
    public ResponseEntity<Product> createProductProductController (@RequestBody @Valid CreateProductRequest requestCreateProductController){
        Product createdProductProductController = productService.createProduct(requestCreateProductController);
        return new ResponseEntity<>(createdProductProductController, HttpStatus.CREATED);
    }
}
