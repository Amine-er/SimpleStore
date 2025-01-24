package com.errabi.productcomposite.service;

import com.errabi.productcomposite.dtos.ProductAggregateDto;
import com.errabi.productcomposite.dtos.ReviewPage;
import com.errabi.productservice.exceptions.EntityNotFoundException;
import com.errabi.productservice.web.dtos.ProductDTO;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCompositeService {

    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${review.service.url}")
    private String reviewServiceUrl;

    public ProductAggregateDto getProductAggregate(Long productId) {
        ProductDTO product = restTemplate.getForObject(productServiceUrl + "/api/products/{id}", ProductDTO.class, productId);
        Page<ReviewDto> reviewPage = getAllReviewsByProductId(productId, PageRequest.of(0, 10));

        if (product == null) {
            throw new EntityNotFoundException("Product not found for ID: " + productId);
        }

        return ProductAggregateDto.builder()
                .product(product)
                .reviews(reviewPage)
                .build();
    }

    private Page<ReviewDto> getAllReviewsByProductId(Long productId, Pageable pageable) {
        String url = reviewServiceUrl + "/api/reviews/product/{productId}?page={page}&size={size}";
        List<ReviewDto> reviews = new ArrayList<>();
        int totalPages;
        int currentPage = pageable.getPageNumber();

        do {
            ResponseEntity<ReviewPage> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    },
                    productId, currentPage, pageable.getPageSize()
            );

            ReviewPage reviewPage = response.getBody();
            if (reviewPage != null) {
                reviews.addAll(reviewPage.getContent());
                totalPages = reviewPage.getTotalPages();
                currentPage++;
            } else {
                break;
            }
        } while (currentPage < totalPages);

        return new PageImpl<>(reviews, pageable, reviews.size());
    }
}
