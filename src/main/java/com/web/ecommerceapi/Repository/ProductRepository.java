package com.web.ecommerceapi.Repository;

import com.web.ecommerceapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
