package com.errabi.productmgt.services;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.exceptions.EntityNotFoundException;
import com.errabi.productmgt.repositories.SimpleProductRepository;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import com.errabi.productmgt.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.errabi.productmgt.utils.ProductConstant.SYSTEM_ERROR_DESCRIPTION;
import static com.errabi.productmgt.web.mapper.ProductMapper.buildSuccessResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final SimpleProductRepository productRepository;
    private final ProductMapper productMapper;

    public ResponseInfo saveProduct(ProductDTO productDto) {
        log.info("Saving product {}", productDto);
        Product productToSave = productMapper.toEntity(productDto);
        productRepository.save(productToSave);
       return buildSuccessResponse(productDto);
    }

    public ResponseInfo updateProduct(ProductDTO productDto) {
        log.info("Updating product {}", productDto);
        Product productToUpdate = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        productMapper.updateFromDto(productDto, productToUpdate);
        productRepository.update(productToUpdate);
        return buildSuccessResponse();
    }

    public ResponseInfo deleteProduct(Long id) {
        log.info("Deleting product {}", id);
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        productRepository.deleteById(productToDelete.getId());
        return buildSuccessResponse();
    }

    public ResponseInfo getProductById(Long id) {
        log.info("Fetching product {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        return buildSuccessResponse(productMapper.toDto(product));
    }

    public ResponseInfo getAllProducts(Pageable pageable) {
        log.info("Fetching all products");
        Page<Product> productPage = productRepository.findAll(pageable);
        if(!productPage.isEmpty()){
            return buildSuccessResponse(productPage.map(productMapper::toDto));
        }else{
            return buildSuccessResponse(Page.empty());
        }
    }
}
