package com.pedrone.products.repositories;

import com.pedrone.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE (:q IS NULL " +
            "       OR " +
            "       upper(p.name) LIKE %:q% " +
            "       OR" +
            "       upper(p.description) LIKE %:q%)" +
            "  AND p.price BETWEEN nvl(:minPrice, p.price) AND nvl(:maxPrice, p.price)")
    public List<Product> search(@Param("q") String q, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}
