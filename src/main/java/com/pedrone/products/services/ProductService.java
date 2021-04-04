package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import com.pedrone.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        } else {
            return null;
        }
    }

    public List<Product> search(String q, Double minPrice, Double maxPrice){
        return productRepository.search(q, minPrice, maxPrice);
    }

    public Product save(Product product) {
        Product savedProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return productRepository.save(savedProduct);
    }

    public Product update(String id, Product product){
        Product productUpdated = findById(id);

        if (productUpdated != null) {
            productUpdated.setName(product.getName());
            productUpdated.setDescription(product.getDescription());
            productUpdated.setPrice(product.getPrice());
            productRepository.flush();
            return productUpdated;
        } else {
            return null;
        }
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
