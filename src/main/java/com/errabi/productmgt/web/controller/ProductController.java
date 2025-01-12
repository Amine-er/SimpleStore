package com.errabi.productmgt.web.controller;

import com.errabi.productmgt.services.ProductService;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseInfo> saveProduct(@RequestBody @Valid ProductDTO productDto) {
        ResponseInfo response = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseInfo> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDto) {
        productDto.setId(id);
        ResponseInfo response = productService.updateProduct(productDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseInfo> deleteProduct(@PathVariable Long id) {
        ResponseInfo response = productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseInfo> getProductById(@PathVariable Long id) {
        ResponseInfo response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseInfo> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int pageSize){
        ResponseInfo response = productService.getAllProducts(PageRequest.of(page,pageSize));
        return ResponseEntity.ok(response);
    }
}
