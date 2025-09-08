package com.ecommerce.blossom.api.blossom_api.services;

import com.ecommerce.blossom.api.blossom_api.dtos.BestSellingProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductSearchRequestDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.mappers.ProductMapper;
import com.ecommerce.blossom.api.blossom_api.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        List<Product> products = List.of(new Product());
        List<ProductDTO> dtos = List.of(new ProductDTO());

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDtoList(products)).thenReturn(dtos);

        List<ProductDTO> result = productService.findAll();

        assertEquals(1, result.size());
        verify(productRepository).findAll();
        verify(productMapper).toDtoList(products);
    }

    @Test
    void findById() {
        Product product = new Product();
        product.setId(1L);
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(dto);

        ProductDTO result = productService.findById(1L);

        assertEquals(1L, result.getId());
        verify(productRepository).findById(1L);
    }

    @Test
    void updateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        Product existing = new Product();
        existing.setId(1L);
        Product updated = new Product();
        updated.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productMapper.toEntity(dto)).thenReturn(updated);

        productService.updateProduct(dto);

        verify(productRepository).save(updated);
    }

    @Test
    void deleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void saveProduct() {
        ProductDTO dto = new ProductDTO();
        Product entity = new Product();

        when(productMapper.toEntity(dto)).thenReturn(entity);

        productService.saveProduct(dto);

        verify(productRepository).save(entity);
    }

    @Test
    void searchProducts() {
        ProductSearchRequestDTO request = new ProductSearchRequestDTO();
        request.setPage(0);
        request.setSize(2);
        request.setSortBy("name");
        request.setSortDirection("ASC");

        Product product = new Product();
        ProductDTO dto = new ProductDTO();
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(productPage);
        when(productMapper.toDto(product)).thenReturn(dto);

        Page<ProductDTO> result = productService.searchProducts(request);

        assertEquals(1, result.getTotalElements());
        verify(productRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void getBestSellingProducts() {
        List<BestSellingProductDTO> bestSellers = List.of(new BestSellingProductDTO("Product A", 10L));

        when(productRepository.findBestSellingProducts()).thenReturn(bestSellers);

        List<BestSellingProductDTO> result = productService.getBestSellingProducts();

        assertEquals(1, result.size());
        assertEquals("Product A", result.get(0).getName());
        verify(productRepository).findBestSellingProducts();
    }
}