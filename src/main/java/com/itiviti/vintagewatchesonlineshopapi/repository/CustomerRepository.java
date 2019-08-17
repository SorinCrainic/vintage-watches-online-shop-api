package com.itiviti.vintagewatchesonlineshopapi.repository;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
