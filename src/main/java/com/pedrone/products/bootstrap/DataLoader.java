package com.pedrone.products.bootstrap;

import com.pedrone.products.domain.Product;
import com.pedrone.products.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;

    public DataLoader(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        Product product1 = Product.builder().id("1").name("product_1").description("product_1").price(new Double(1)).build();
        productService.save(product1);

        Product product2 = Product.builder().id("2").name("product_2").description("product_2").price(new Double(2)).build();
        productService.save(product2);

        System.out.println("Products loaded: " + productService.findAll().size());

    }
}
