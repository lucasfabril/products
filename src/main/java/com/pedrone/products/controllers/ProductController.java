package com.pedrone.products.controllers;

import com.pedrone.products.domain.Product;
import com.pedrone.products.services.ProductService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id){
        Product product = productService.findById(id);
        if (product != null){
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(value = "q", required = false) String q,
                                                @RequestParam(value = "min_price", required = false) Double minPrice,
                                                @RequestParam(value = "max_price", required = false) Double maxPrice){

        if (q != null) {
            q = q.toUpperCase();
        }

        List<Product> products = productService.search(q, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public void save(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody Product product){
        Product productUpdated = productService.findById(id);
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
