package com.errabi.productcomposite.controller;

import com.errabi.productcomposite.dtos.ProductAggregateDto;
import com.errabi.productcomposite.service.ProductCompositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/composite/products")
@RequiredArgsConstructor
public class ProductCompositeController {

    private final ProductCompositeService productCompositeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductAggregateDto> getProductAggregate(@PathVariable Long id) {
        ProductAggregateDto aggregate = productCompositeService.getProductAggregate(id);
        return ResponseEntity.ok(aggregate);
    }
}
