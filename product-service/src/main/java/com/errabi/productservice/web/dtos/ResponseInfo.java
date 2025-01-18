package com.errabi.productservice.web.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo {
    //Generic
    private String errorCode;
    private String errorDescription;

    //Data output optionals
    private ProductDTO product;
    private Page<ProductDTO> productPage;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
