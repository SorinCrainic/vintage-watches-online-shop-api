package com.itiviti.vintagewatchesonlineshopapi.repository;

import com.itiviti.vintagewatchesonlineshopapi.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}