package com.pedrone.products.controllers;

import com.pedrone.products.domain.Product;
import com.pedrone.products.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") String id){
        return productService.findById(id).get();
    }

    @GetMapping("/search")
    public List<Product> search(){
        return null;
    }

    @PostMapping
    public void save(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody Product product){
        Product productUpdated = productService.findById(id).get();
        productUpdated.setName(product.getName());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setPrice(product.getPrice());

        productService.save(productUpdated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        productService.deleteById(id);
    }

}
