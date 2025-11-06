package com.web.ecommerceapi.Service.ServiceImpl;

import com.web.ecommerceapi.Entity.Product;
import com.web.ecommerceapi.Exceptions.InvalidProductIdException;
import com.web.ecommerceapi.Exceptions.NewProductNameException;
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
        if (repo.existsByProductName(p.getProductName()))
            throw new NewProductNameException("You can't have the same product twice in our database");
        return repo.save(p);
    }

    @Override
    public List<Product> inventory() {

        return repo.findAll();
    }

    @Override
    public Product findProductbyId(long id) {
        return repo.findById(id).orElseThrow(() ->
                new InvalidProductIdException(
                        "The provided product Id doesnt appear on our database"));
    }

    @Override
    public Product update(long id, Product p) {
        Product exists = repo.findById(id).orElseThrow(() -> new InvalidProductIdException("The provided product Id " +
                "doesnt appear on our database"));
        if(exists.getProductName().equals(p.getProductName())){
            Product up = repo.findById(id).get();
            up.setPrice(p.getPrice());
            return repo.save(up);

        } else {
            throw new NewProductNameException("You are not allowed to change the " +
                    "name of the product");
        }
    }

    public void deleteProduct(long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        else {
            throw new InvalidProductIdException(
                    "The provided product Id doesnt appear on our database"
            );

        }
    }
}
