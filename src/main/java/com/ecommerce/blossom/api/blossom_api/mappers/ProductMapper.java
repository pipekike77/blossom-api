package com.ecommerce.blossom.api.blossom_api.mappers;

import com.ecommerce.blossom.api.blossom_api.dtos.ProductDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO dto);
    ProductDTO toDto(Product entity);
    List<ProductDTO> toDtoList(List<Product> products);
    //ProductResponseDTO toDto(Product product);
}
