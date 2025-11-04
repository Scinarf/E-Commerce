package com.web.ecommerceapi.Service;

import com.web.ecommerceapi.Entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product p);
    List<Product> inventory ();
}
