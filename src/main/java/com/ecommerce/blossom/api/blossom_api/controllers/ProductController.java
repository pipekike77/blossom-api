package com.ecommerce.blossom.api.blossom_api.controllers;

import com.ecommerce.blossom.api.blossom_api.dtos.BestSellingProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductSearchRequestDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Product;
import com.ecommerce.blossom.api.blossom_api.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(summary = "Get product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id); // lanza excepción si no existe
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        productService.updateProduct(productDTO);
        return ResponseEntity.ok("Product updated successfully");
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @Operation(summary = "Search for products with filters and pagination")
    @PostMapping("/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(@Valid @RequestBody ProductSearchRequestDTO request) {
        return ResponseEntity.ok(productService.searchProducts(request));
    }

    @Operation(summary = "Get best-selling products", description = "Returns a list of the top-selling products based on quantity sold")
    @ApiResponse(responseCode = "200", description = "List of best-selling products retrieved successfully")

    @GetMapping("/best-selling")
    public ResponseEntity<List<BestSellingProductDTO>> getBestSellingProducts() {
        return ResponseEntity.ok(productService.getBestSellingProducts());
    }

}
