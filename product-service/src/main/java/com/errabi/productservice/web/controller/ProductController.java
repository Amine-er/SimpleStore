package com.errabi.productservice.web.controller;

import com.errabi.productservice.services.ProductService;
import com.errabi.productservice.web.dtos.ProductDTO;
import com.errabi.productservice.web.dtos.ResponseInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Management", description = "Operations related to product management")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product and returns the response info")
    public ResponseEntity<ResponseInfo> saveProduct(@RequestBody @Valid ProductDTO productDto) {
        ResponseInfo response = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Updates an existing product by ID and returns the response info")
    public ResponseEntity<ResponseInfo> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDto) {
        productDto.setId(id);
        ResponseInfo response = productService.updateProduct(productDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Retrieves a product by ID and returns the response info")
    public ResponseEntity<ResponseInfo> getProductById(@PathVariable Long id) {
        ResponseInfo response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves all products with pagination and returns the response info")
    public ResponseEntity<ResponseInfo> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseInfo response = productService.getAllProducts(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(response);
    }
}
