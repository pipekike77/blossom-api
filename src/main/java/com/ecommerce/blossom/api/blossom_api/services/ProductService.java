package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.BestSellingProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductSearchRequestDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.exceptions.ResourceNotFoundException;
import com.ecommerce.blossom.api.blossom_api.filters.ProductSpecification;
import com.ecommerce.blossom.api.blossom_api.mappers.ProductMapper;
import com.ecommerce.blossom.api.blossom_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> findAll(){
        return productMapper.toDtoList(productRepository.findAll());
    }

    public ProductDTO findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        return productMapper.toDto(product);
    }

    public void updateProduct(ProductDTO productDTO){
        Product existing = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productDTO.getId() + " not found"));

        Product updated = productMapper.toEntity(productDTO);
        updated.setId(existing.getId());
        productRepository.save(updated);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    public void saveProduct(ProductDTO product){
        productRepository.save(productMapper.toEntity(product));
    }

    public Page<ProductDTO> searchProducts(ProductSearchRequestDTO request) {
        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortBy())
        );

        Specification<Product> spec = ProductSpecification.withFilters(
                request.getName(),
                request.getCategory(),
                request.getMinPrice(),
                request.getMaxPrice()
        );

        Page<Product> products = productRepository.findAll(spec, pageable);
        return products.map(productMapper::toDto);
    }

    public List<BestSellingProductDTO> getBestSellingProducts() {
        return productRepository.findBestSellingProducts();
    }
}
