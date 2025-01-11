package com.errabi.productmgt.web.dtos;

import com.errabi.productmgt.enums.Category;
import com.errabi.productmgt.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Status status;
    private Category category;
    private LocalDateTime createdAt;
}
