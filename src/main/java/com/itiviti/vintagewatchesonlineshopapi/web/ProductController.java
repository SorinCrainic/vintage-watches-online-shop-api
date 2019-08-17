package com.itiviti.vintagewatchesonlineshopapi.web;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.ProductNotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.service.ProductService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.FindProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.UpdateProductRequest;
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

    //endpoint: POST (add/create) new product
    @PostMapping
    public ResponseEntity<Product> createProductProductController(@RequestBody @Valid CreateProductRequest requestCreateProductController) {
        Product createdProductProductController = productService.createProduct(requestCreateProductController);
        return new ResponseEntity<>(createdProductProductController, HttpStatus.CREATED);
    }

    //endpoint: GET (read/retrieve) existing product
    @GetMapping
    public ResponseEntity<Page<Product>> findProducts(FindProductRequest requestGetProductController, Pageable pageable) {
        Page<Product> responseProductController = productService.findProducts(requestGetProductController, pageable);
        return new ResponseEntity<>(responseProductController, HttpStatus.OK);
    }

    //endpoint: GET (read/select) existing product
    @GetMapping("/{toSelectProductId}")
    public ResponseEntity getProductProductController(@PathVariable("toSelectProductId") Long id) throws ProductNotFoundException {
        Product selectedProduct = productService.getProduct(id);
        return new ResponseEntity<>(selectedProduct, HttpStatus.OK);
    }

    //endpoint: DELETE existing product
    @DeleteMapping("/{toDeleteProductId}")
    public ResponseEntity deleteProductProductController(@PathVariable("toDeleteProductId") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //endpoint: PUT (update) existing product
    @PutMapping("/{toUpdateProductId}")
    public ResponseEntity updateProductProductController(@PathVariable("toUpdateProductId") Long id,@RequestBody @Valid  UpdateProductRequest requestUpdateProductController) throws ProductNotFoundException {
        productService.updateProduct(id, requestUpdateProductController);
        return new ResponseEntity(HttpStatus.OK);
    }
}
