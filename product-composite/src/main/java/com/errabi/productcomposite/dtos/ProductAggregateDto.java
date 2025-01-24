package com.errabi.productcomposite.dtos;

import com.errabi.productservice.web.dtos.ProductDTO;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class ProductAggregateDto {
    private ProductDTO product;
    private Page<ReviewDto> reviews;
}
