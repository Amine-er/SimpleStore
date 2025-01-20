package com.errabi.reviewservice.web.controller;

import com.errabi.reviewservice.services.ReviewService;
import com.errabi.reviewservice.web.dtos.ResponseInfo;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review Management", description = "Operations related to review management")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Create a new review", description = "Creates a new review for a product and returns the response info")
    public ResponseEntity<ResponseInfo> saveReview(@RequestBody @Valid ReviewDto reviewDto) {
        ResponseInfo response = reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing review", description = "Updates an existing review by ID and returns the response info")
    public ResponseEntity<ResponseInfo> updateReview(@PathVariable String id, @RequestBody @Valid ReviewDto reviewDto) {
        reviewDto.setId(id);
        ResponseInfo response = reviewService.updateReview(reviewDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review", description = "Deletes a review by ID")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get all reviews for a product", description = "Retrieves all reviews for a product with pagination and returns the response info")
    public ResponseEntity<ResponseInfo> getAllReviews(@PathVariable Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        ResponseInfo response = reviewService.getAllReviewsByProductId(productId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(response);
    }
}
