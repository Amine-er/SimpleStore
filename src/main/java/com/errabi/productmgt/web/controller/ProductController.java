package com.errabi.productmgt.web.controller;

import com.errabi.productmgt.services.ProductService;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResponseInfo> saveProduct(@RequestBody @Valid ProductDTO productDto) {
        ResponseInfo response = productService.saveProduct(productDto);
        return ResponseEntity.ok(response);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<ResponseInfo> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDto) {
        productDto.setId(id);
        ResponseInfo response = productService.updateProduct(productDto);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseInfo> deleteProduct(@PathVariable Long id) {
        ResponseInfo response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseInfo> getProductById(@PathVariable Long id) {
        ResponseInfo response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<ResponseInfo> getAllProducts() {
        ResponseInfo response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }
}
