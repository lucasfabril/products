package com.pedrone.products.services;

import com.pedrone.products.domain.Product;
import com.pedrone.products.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static final String PRODUCT_ID = "1";
    public static final Double PRODUCT_PRICE = new Double(1);

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product product;

    List<Product> products = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        product = Product.builder().id(PRODUCT_ID).name("product1").description("product1").price(PRODUCT_PRICE).build();
        products.add(product);
        products.add(Product.builder().id("2").name("product2").description("product2").price(new Double(2)).build());
    }

    @Test
    public void findAll(){
        when(productRepository.findAll()).thenReturn(products);

        List<Product> listProducts = productService.findAll();
        assertEquals(2, listProducts.size());
    }

    @Test
    public void findById(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        Product product1 = productService.findById(PRODUCT_ID);
        assertNotNull(product1);
    }

    @Test
    public void findByIdNotFound(){
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        Product product1 = productService.findById(PRODUCT_ID);
        assertNull(product1);
    }

    @Test
    public void search(){
        when(productRepository.search(anyString(), anyDouble(), anyDouble())).thenReturn(products);

        List<Product> productsList = productService.search("p", Double.valueOf("1"), Double.valueOf("2"));
        assertEquals(2, productsList.size());
    }

    @Test
    public void searchWithNoParameters(){
        when(productRepository.search(null, null, null)).thenReturn(products);

        List<Product> productsList = productService.search(null, null, null);
        assertEquals(2, productsList.size());
    }

    @Test
    public void save(){
        when(productRepository.save(any())).thenReturn(product);

        Product savedProduct = productService.save(Product.builder().build());
        assertNotNull(savedProduct);
    }

    @Test
    public void update(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        Product productUpdated = productService.update(PRODUCT_ID, Product
                .builder()
                .id("2")
                .name("name_updated")
                .description("desc_updated")
                .price(Double.valueOf("2"))
                .build());

        assertEquals(PRODUCT_ID, productUpdated.getId());
        assertEquals("name_updated", productUpdated.getName());
        assertEquals("desc_updated", productUpdated.getDescription());
        assertEquals(Double.valueOf("2"), productUpdated.getPrice());
    }

    @Test
    public void updateProductNotFound(){
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        Product productUpdated = productService.update(PRODUCT_ID, Product.builder().build());
        assertNull(productUpdated);
    }

    @Test
    public void deleteById(){
        productService.deleteById(PRODUCT_ID);
        verify(productRepository).deleteById(anyString());
    }


}
