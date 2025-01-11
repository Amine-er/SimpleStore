package com.errabi.productmgt.services;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.exceptions.NotFoundException;
import com.errabi.productmgt.repositories.ProductRepository;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import com.errabi.productmgt.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ResponseInfo saveProduct(ProductDTO productDto) {
        log.info("Saving product {}", productDto.getId());
        Product productToSave = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .status(productDto.getStatus())
                .category(productDto.getCategory())
                .build();
        productRepository.save(productToSave);
        return ResponseInfo.builder()
                .status(200)
                .message("Product saved successfully").build();
    }

    public ResponseInfo updateProduct(ProductDTO productDto) {
        log.info("Updating product {}", productDto.getId());
        Product productToUpdate = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productMapper.updateFromDto(productDto, productToUpdate);
        productRepository.save(productToUpdate);
        return ResponseInfo.builder()
                .status(200)
                .message("Product updated successfully").build();
    }

    public ResponseInfo deleteProduct(Long id) {
        log.info("Deleting product {}", id);
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.deleteById(productToDelete.getId());
        return ResponseInfo.builder()
                .status(200)
                .message("Product deleted successfully").build();
    }

    public ResponseInfo getProductById(Long id) {
        log.info("Fetching product {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return ResponseInfo.builder()
                .status(200)
                .message("Product fetched successfully")
                .product(productMapper.toDto(product))
                .build();
    }

    public ResponseInfo getAllProducts() {
        log.info("Fetching all products");
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ProductDTO> productDtos = productMapper.toDtos(productList);
        return ResponseInfo.builder()
                .status(200)
                .message("Products fetched successfully")
                .products(productDtos)
                .build();
    }
}
