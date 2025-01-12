package com.errabi.productmgt.services;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.exceptions.EntityNotFoundException;
import com.errabi.productmgt.repositories.ProductRepository;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import com.errabi.productmgt.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.errabi.productmgt.utils.ProductConstant.SYSTEM_ERROR_DESCRIPTION;
import static com.errabi.productmgt.web.mapper.ProductMapper.buildSuccessResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ResponseInfo saveProduct(ProductDTO productDto) {
        log.info("Saving product {}", productDto);
        Product productToSave = productMapper.toEntity(productDto);
        productRepository.save(productToSave);
       return buildSuccessResponse(productDto);
    }

    @Transactional
    public ResponseInfo updateProduct(ProductDTO productDto) {
        log.info("Updating product {}", productDto);
        Product productToUpdate = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        productMapper.updateFromDto(productDto, productToUpdate);
        productRepository.save(productToUpdate);
        return buildSuccessResponse();
    }

    @Transactional
    public ResponseInfo deleteProduct(Long id) {
        log.info("Deleting product {}", id);
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        productRepository.deleteById(productToDelete.getId());
        return buildSuccessResponse();
    }

    @Transactional(readOnly = true)
    public ResponseInfo getProductById(Long id) {
        log.info("Fetching product {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        return buildSuccessResponse(productMapper.toDto(product));
    }

    @Transactional(readOnly = true)
    public ResponseInfo getAllProducts() {
        log.info("Fetching all products");
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ProductDTO> productDtos = productMapper.toDtos(productList);
        return buildSuccessResponse(productDtos);
    }
}
