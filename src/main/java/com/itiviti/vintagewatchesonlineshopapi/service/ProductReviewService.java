package com.itiviti.vintagewatchesonlineshopapi.service;

import com.itiviti.vintagewatchesonlineshopapi.domain.ProductReview;
import com.itiviti.vintagewatchesonlineshopapi.repository.ProductReviewRepository;
import com.itiviti.vintagewatchesonlineshopapi.transfer.productReview.CreateProductReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewService.class);

    //IoC (Inversion on Control) and DI (Dependency Injection)
    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    //Method for CREATING a product review (Crud)
    public ProductReview createProductReview(CreateProductReviewRequest requestCreateProductReview) {

        LOGGER.info("Creating product review {}", requestCreateProductReview);

        ProductReview productReview = new ProductReview();

        productReview.setProduct(requestCreateProductReview.getProduct());
        productReview.setReviewContent(requestCreateProductReview.getReviewContent());

        return productReviewRepository.save(productReview);
    }
}
