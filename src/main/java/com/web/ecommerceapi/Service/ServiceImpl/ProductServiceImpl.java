package com.web.ecommerceapi.Service.ServiceImpl;

import com.web.ecommerceapi.Entity.Product;
import com.web.ecommerceapi.Repository.ProductRepository;
import com.web.ecommerceapi.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product createProduct(Product p) {
        return repo.save(p);
    }

    public List<Product> inventory (){
        return repo.findAll();
    }
}
