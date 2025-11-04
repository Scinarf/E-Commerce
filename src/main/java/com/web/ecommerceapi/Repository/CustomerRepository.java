package com.web.ecommerceapi.Repository;

import com.web.ecommerceapi.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
