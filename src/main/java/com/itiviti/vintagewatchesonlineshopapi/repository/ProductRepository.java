package com.itiviti.vintagewatchesonlineshopapi.repository;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Long is wrapper class for primitive type long
//since ProductRepository is an interface, not a class, will define just method signature
public interface ProductRepository extends JpaRepository<Product, Long> {

    //queries derived from method names (using Spring Boot)
    //simple query: find product by partial name
    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    //complex query: find product by partial name and by minimum quantity
    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String partialName, Integer minQuantity, Pageable pageable);

    //named query using JPQL (Java Persistence Query Language)
    //@Query("SELECT product FROM Product queriedProduct WHERE name LIKE '%?1'")

    //named query using native SQL query
    @Query(value = "SELECT * FROM product WHERE name LIKE '%?1'", nativeQuery = true)
    Page<Product> findByPartialNameManualQuerry(String productPartialName, Pageable pageable);
}