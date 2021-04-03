package com.pedrone.products.repositories;

import com.pedrone.products.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
