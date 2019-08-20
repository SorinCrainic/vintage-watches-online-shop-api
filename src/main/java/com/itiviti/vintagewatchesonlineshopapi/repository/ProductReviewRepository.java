package com.itiviti.vintagewatchesonlineshopapi.repository;

import com.itiviti.vintagewatchesonlineshopapi.domain.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    //queries by nested properties
    Page<ProductReview> findByProductId(Long productID, Pageable pageable);
}
