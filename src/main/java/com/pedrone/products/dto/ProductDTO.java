package com.pedrone.products.dto;

import com.pedrone.products.domain.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private Double price;

    public static ProductDTO create(Product product){
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

}
