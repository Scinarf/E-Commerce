package com.web.ecommerceapi.Controller;

import com.web.ecommerceapi.Entity.Product;
import com.web.ecommerceapi.Service.ServiceImpl.ProductServiceImpl;
import jakarta.validation.Valid;
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
    public void newProduct(@RequestBody @Valid Product p){
         service.createProduct(p);
    }

    @GetMapping("/inventory")
    public List<Product> productList(){
        return service.inventory();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable ("id") long id){
        return service.findProductbyId(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable ("id")long id, @RequestBody @Valid Product p){
        service.update(id,p);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable ("id") long id){
        service.deleteProduct(id);
    }
}
