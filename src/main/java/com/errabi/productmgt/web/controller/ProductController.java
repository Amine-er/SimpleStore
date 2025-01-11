package com.errabi.productmgt.web.controller;

import com.errabi.productmgt.services.ProductService;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseInfo> saveProduct(@RequestBody @Valid ProductDTO productDto) {
        ResponseInfo response = productService.saveProduct(productDto);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseInfo> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDto) {
        productDto.setId(id); // Ensure the DTO has the correct ID
        ResponseInfo response = productService.updateProduct(productDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfo> deleteProduct(@PathVariable Long id) {
        ResponseInfo response = productService.deleteProduct(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseInfo> getProductById(@PathVariable Long id) {
        ResponseInfo response = productService.getProductById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseInfo> getAllProducts() {
        ResponseInfo response = productService.getAllProducts();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
