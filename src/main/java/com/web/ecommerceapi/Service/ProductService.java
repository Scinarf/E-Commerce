package com.web.ecommerceapi.Service;

import com.web.ecommerceapi.Entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product p);
    List<Product> inventory ();
    Product findProductbyId (long id);
    Product update (long id, Product p);
    void deleteProduct(long id);
}
