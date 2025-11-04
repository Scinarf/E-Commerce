package com.web.ecommerceapi.Controller;

import com.web.ecommerceapi.Entity.Product;
import com.web.ecommerceapi.Service.ServiceImpl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductServiceImpl service;

    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }
    @PostMapping("/new")
    public Product newProduct(@RequestBody Product p){
        return service.createProduct(p);
    }

    @GetMapping("/inventory")
    public List<Product> productList(){
        return service.inventory();
    }
}
