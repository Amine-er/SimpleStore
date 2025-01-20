package com.errabi.reviewservice.web.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo {
    private String errorCode;
    private String errorDescription;

    private ReviewDto reviewDto;
    private Page<ReviewDto> reviewPage;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
