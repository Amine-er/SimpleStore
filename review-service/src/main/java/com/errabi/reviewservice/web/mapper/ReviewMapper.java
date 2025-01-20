package com.errabi.reviewservice.web.mapper;

import com.errabi.reviewservice.entities.Review;
import com.errabi.reviewservice.web.dtos.ResponseInfo;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import static com.errabi.reviewservice.utils.ReviewConstant.SUCCESS_CODE;
import static com.errabi.reviewservice.utils.ReviewConstant.SUCCESS_CODE_DESCRIPTION;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    Review toEntity(ReviewDto reviewDto);
    ReviewDto toDto(Review review);
    void updateFromDto(ReviewDto reviewDto, @MappingTarget Review review);

    static ResponseInfo buildSuccessResponse(ReviewDto reviewDto){
        return ResponseInfo.builder()
                .errorCode(SUCCESS_CODE)
                .errorDescription(SUCCESS_CODE_DESCRIPTION)
                .reviewDto(reviewDto)
                .build();
    }

    static ResponseInfo buildSuccessResponse(Page<ReviewDto> reviews){
        return ResponseInfo.builder()
                .errorCode(SUCCESS_CODE)
                .errorDescription(SUCCESS_CODE_DESCRIPTION)
                .reviewPage(reviews)
                .build();
    }

    static ResponseInfo buildSuccessResponse(){
        return ResponseInfo.builder()
                .errorCode(SUCCESS_CODE)
                .errorDescription(SUCCESS_CODE_DESCRIPTION)
                .build();
    }
}
