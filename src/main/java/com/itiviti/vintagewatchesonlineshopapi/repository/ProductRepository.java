package com.itiviti.vintagewatchesonlineshopapi.repository;

import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Long is wrapper class for primitive type long
public interface ProductRepository extends JpaRepository<Product, Long> {



}
