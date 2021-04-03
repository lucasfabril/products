package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService extends JpaRepository<Product, String> {

    List<Product> findAll();

    Product findById();

    Product save(Product product);

    void deleteById(String id);
}
