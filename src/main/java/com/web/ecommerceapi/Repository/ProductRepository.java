package com.web.ecommerceapi.Repository;

import com.web.ecommerceapi.Entity.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);

    boolean existsById(long id);
}
