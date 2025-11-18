package com.web.ecommerceapi.Service.ServiceImpl;

import com.web.ecommerceapi.Entity.Product;
import com.web.ecommerceapi.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository repo;
    @InjectMocks
    private ProductServiceImpl service;

    private Product p;

   private List<Product> pro;

   @BeforeEach
   public void setUp(){
       p = Product.builder()
               .productName("pap")
               .price(10).build();
       pro  = new ArrayList<>();
       pro.add(Product.builder()
               .productName("pap")
               .price(10).build());
       pro.add(Product.builder()
               .productName("cool drink")
               .price(15).build());
   }

    @Test
    void createProductWithCorrectDetails() {
        when(repo.save(any(Product.class))).thenReturn(p);
        Product myProduct = service.createProduct(p);
        assertEquals(p.getProductName(),myProduct.getProductName());

    }

    @Test
    void createProductWithWrongDetails() {
        when(repo.save(any(Product.class))).thenReturn(p);
        Product myProduct = service.createProduct(p);
        assertNotEquals("cool drink",myProduct.getProductName());

    }

    @Test
    void inventory() {
       when(repo.findAll()).thenReturn(pro);
       List<Product> listings = service.inventory().stream().toList();
       assertEquals(pro.get(0).getProductName(),listings.get(0).getProductName());
        assertEquals(pro.get(1).getProductName(),listings.get(1).getProductName());
    }

    @Test
    void inventoryWithWrongDetails() {
        when(repo.findAll()).thenReturn(pro);
        List<Product> listings = service.inventory().stream().toList();
        assertNotEquals(pro.get(1).getProductName(),listings.get(0).getProductName());
        assertNotEquals(pro.get(0).getProductName(),listings.get(1).getProductName());
    }

    @Test
    void findProductbyId() {
       when(repo.findById(1L)).thenReturn(Optional.ofNullable(p));
       Product prod = service.findProductbyId(1L);
       assertEquals(p.getId(),prod.getId());
        assertEquals(p.getProductName(),prod.getProductName());
        assertEquals(p.getPrice(),prod.getPrice());
    }

    @Test
    void findProductbyWrongId() {
        when(repo.findById(longThat(a -> a != 1L))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,() ->
                service.findProductbyId(2L));
        assertEquals("The provided product Id doesnt appear on our database",exception.getMessage());
        }

    @Test
    void update() {
       when(repo.findById(1L)).thenReturn(Optional.ofNullable(p));
       Product s = Product.builder()
               .productName("pap")
               .price(20)
               .build();
//       when(repo.save(s)).thenReturn(s);
        //Simulation, whatever value is passed in, return it back as the answer
        double oldPrice = p.getPrice();
        when(repo.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        Product prod = service.update(1L,s);
       assertEquals("pap",prod.getProductName());

       assertNotEquals(oldPrice, prod.getPrice());
    }

    @Test
    void updateWithNewName() {
        when(repo.findById(1L)).thenReturn(Optional.ofNullable(p));
        Product s = Product.builder()
                .productName("chillie")
                .price(46)
                .build();

// get an error and save it when we change the name of the product
        RuntimeException exception = assertThrows(RuntimeException.class,() ->
                service.update(1L,s));
        assertEquals("You are not allowed to change the " +
                "name of the product",exception.getMessage());
    }

    @Test
    void deleteProduct() {
       //Mock the repo and tell it what to do when called and the second part is for after the deletion process
       when(repo.existsById(1L)).thenReturn(true)
               .thenReturn(false);

       //The delete method is void so it returns nothing but deletes the entity
       doNothing().when(repo).deleteById(1L);
       service.deleteProduct(1L);
       //The entity is deleted so we need to get an exception because the entity is no more
        RuntimeException exception = assertThrows(RuntimeException.class,() ->
                service.deleteProduct(1L));
        assertEquals("The provided product Id doesnt appear on our database",exception.getMessage());
    }
}