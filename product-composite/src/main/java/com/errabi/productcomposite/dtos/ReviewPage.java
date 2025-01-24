package com.errabi.productcomposite.dtos;

import com.errabi.reviewservice.web.dtos.ReviewDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ReviewPage extends PageImpl<ReviewDto> {

    @JsonCreator
    public ReviewPage(@JsonProperty("content") List<ReviewDto> content,
                      @JsonProperty("number") int number,
                      @JsonProperty("size") int size,
                      @JsonProperty("totalElements") long totalElements,
                      @JsonProperty("pageable") @JsonDeserialize(using = PageableDeserializer.class) Pageable pageable) {
        super(content, PageRequest.of(number, size), totalElements);
    }

}
