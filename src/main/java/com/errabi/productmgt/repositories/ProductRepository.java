package com.errabi.productmgt.repositories;

import com.errabi.productmgt.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
