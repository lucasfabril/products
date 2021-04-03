package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService extends CrudService<Product, String> {

}
