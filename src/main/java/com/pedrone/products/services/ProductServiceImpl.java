package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import com.pedrone.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> findAll() {
        return (Set<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
