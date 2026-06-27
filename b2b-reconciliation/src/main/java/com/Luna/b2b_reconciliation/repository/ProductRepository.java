package com.Luna.b2b_reconciliation.repository;

import com.Luna.b2b_reconciliation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository ya incluye métodos como save(), findAll(), findById(), etc.
}
