package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import com.pedrone.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product findById(String id) {
        return productRepository.findById(id).get();
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
