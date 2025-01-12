package com.errabi.productmgt.web.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo {
    //Generic
    private String errorCode;
    private String errorDescription;

    //Data output optionals
    private ProductDTO product;
    private List<ProductDTO> products;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
