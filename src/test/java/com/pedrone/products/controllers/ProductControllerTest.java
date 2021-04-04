package com.pedrone.products.controllers;

import com.pedrone.products.ProductsApplication;
import com.pedrone.products.domain.Product;
import com.pedrone.products.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProductsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    ProductService productService;

    private ResponseEntity<Product> getProduct(String url) {
        return rest.getForEntity(url, Product.class);
    }

    private ResponseEntity<List<Product>> getProducts(String url) {
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
    }

    @Test
    public void testFindAll() {
        List<Product> products = getProducts("/products").getBody();
        assertNotNull(products);
        assertEquals(4, products.size());
    }

    @Test
    public void findById(){
        Product product = getProduct("/products/1").getBody();

        assertEquals("1", product.getId());
        assertEquals("product_1", product.getName());
        assertEquals("product_1", product.getDescription());
        assertEquals(1.0, product.getPrice());
    }

    @Test
    public void findByIdNotFound(){
        ResponseEntity response = getProduct("/products/5");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void search(){
        List<Product> products = getProducts("/products/search?min_price=2&max_price=3&q=prod").getBody();
        assertEquals(2, products.size());
    }

    @Test
    public void searchWithNoParameters(){
        List<Product> products = getProducts("/products/search").getBody();
        assertEquals(4, products.size());
    }

    @Test
    public void saveAndDelete(){
        Product newProduct = Product.builder().id("5").name("product_5").description("product_5").price(5.0).build();

        //Adding the new product
        ResponseEntity response = rest.postForEntity("/products", newProduct, null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //Verifying new product
        String location = response.getHeaders().get("location").get(0);
        newProduct = getProduct(location).getBody();
        assertNotNull(newProduct);
        assertEquals("5", newProduct.getId());
        assertEquals("product_5", newProduct.getName());
        assertEquals("product_5", newProduct.getDescription());
        assertEquals(5.0, newProduct.getPrice());

        //Deleting new product
        rest.delete(location);
        assertEquals(HttpStatus.NOT_FOUND, getProduct(location).getStatusCode());
    }

    @Test
    public void update(){
        Product product = Product.builder().name("product_UPDATE").description("product_UPDATE").price(100.0).build();

        rest.put("/products/4", product);
        Product productUpdated = getProduct("/products/4").getBody();
        assertEquals("4", productUpdated.getId());
        assertEquals("product_UPDATE", productUpdated.getName());
        assertEquals("product_UPDATE", productUpdated.getDescription());
        assertEquals(100.0, productUpdated.getPrice());
    }

    @Test
    public void updateNotFound(){
        Product product = Product.builder().name("product_UPDATE").description("product_UPDATE").price(100.0).build();

        rest.put("/products/5", product);
        ResponseEntity response = getProduct("/products/5");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
