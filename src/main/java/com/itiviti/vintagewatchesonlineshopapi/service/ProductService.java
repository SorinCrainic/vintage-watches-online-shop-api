package com.itiviti.vintagewatchesonlineshopapi.service;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.repository.ProductRepository;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.CreateProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.FindProductRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.ProductDTO;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.UpdateProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    //IoC (Inversion on Control) and DI (Dependency Injection)
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Method for CREATING a product (Crud)
    public Product createProduct(CreateProductRequest request) {

        LOGGER.info("Creating product {}", request);

        Product product = new Product();

        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setImagePath(request.getImagePath());
        product.setProductDescription(request.getProductDescription());
        product.setProductRate(request.getProductRate());

        return productRepository.save(product);
    }

    //Method for RETRIEVING a product from db by productId (cRud)
    public Product getProduct(long id) throws NotFoundException {
        LOGGER.info("Retrieving product {}", id);
        //using Optional with orElseThrow; using Lambda expressions (anonymous methods)
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product " + id + " not exist."));
    }

    //Method for UPDATING a product (crUd)
    public Product updateProduct(long id, UpdateProductRequest requestForUpdate) throws NotFoundException {
        LOGGER.info("Update-ing product {} with {}", id, requestForUpdate);
        Product productToBeUpdated = getProduct(id);

        BeanUtils.copyProperties(requestForUpdate, productToBeUpdated);

        return productRepository.save(productToBeUpdated);
    }

    //Method for DELETING a product (cruD)
    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }

    //Queries
    public Page<ProductDTO> findProducts(FindProductRequest request, Pageable pageable) {
        LOGGER.info("Retrieving searched product {}.", request);

        Page<Product> productsRestoredFromDb;
        List<ProductDTO> productDTOS = new ArrayList<>();

        if (request.getPartialName() != null && request.getMinQuantity() != null) {
            productsRestoredFromDb = productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(), request.getMinQuantity(), pageable);
        } else if (request.getPartialName() != null) {
            productsRestoredFromDb = productRepository.findByNameContaining(request.getPartialName(), pageable);
        } else {
            //if no search criteria mentioned, will be displayed all products paginated
            productsRestoredFromDb = productRepository.findAll(pageable);
        }

        productsRestoredFromDb.getContent().forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setProductRate(product.getProductRate());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setImagePath(product.getImagePath());

            productDTOS.add(productDTO);
        });

        return new PageImpl<>(productDTOS, pageable, productsRestoredFromDb.getTotalElements());
    }
}