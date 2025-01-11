package com.errabi.productmgt.web.mapper;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.web.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toEntity(ProductDTO productDto);
    ProductDTO toDto(Product product);
    List<ProductDTO> toDtos(List<Product> products);
    void updateFromDto(ProductDTO productDto, @MappingTarget Product product);
}
