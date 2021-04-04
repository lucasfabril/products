package com.pedrone.products.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull(message = "Mandatory field: name")
    private String name;

    @NotNull(message = "Mandatory field: description")
    private String description;

    @NotNull(message = "Mandatory field: price")
    @Positive(message = "Must inform a positive value for price")
    private Double price;
}