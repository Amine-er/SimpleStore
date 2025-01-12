package com.errabi.productmgt.web.mapper;

import com.errabi.productmgt.entities.Product;
import com.errabi.productmgt.web.dtos.ProductDTO;
import com.errabi.productmgt.web.dtos.ResponseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import static com.errabi.productmgt.utils.ProductConstant.SUCCESS_CODE;
import static com.errabi.productmgt.utils.ProductConstant.SUCCESS_CODE_DESCRIPTION;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toEntity(ProductDTO productDto);
    ProductDTO toDto(Product product);
    void updateFromDto(ProductDTO productDto, @MappingTarget Product product);

    static ResponseInfo buildSuccessResponse(ProductDTO productDTO){
        return ResponseInfo.builder()
                .errorCode(SUCCESS_CODE)
                .errorDescription(SUCCESS_CODE_DESCRIPTION)
                .product(productDTO)
                .build();
    }

    static ResponseInfo buildSuccessResponse(Page<ProductDTO> products){
        return ResponseInfo.builder()
            .errorCode(SUCCESS_CODE)
            .errorDescription(SUCCESS_CODE_DESCRIPTION)
            .productPage(products)
            .build();
    }

    static ResponseInfo buildSuccessResponse(){
        return ResponseInfo.builder()
                .errorCode(SUCCESS_CODE)
                .errorDescription(SUCCESS_CODE_DESCRIPTION)
                .build();
    }
}
