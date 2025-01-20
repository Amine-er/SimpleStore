package com.errabi.reviewservice.services;

import com.errabi.reviewservice.entities.Review;
import com.errabi.reviewservice.exceptions.EntityNotFoundException;
import com.errabi.reviewservice.repositories.ReviewRepository;
import com.errabi.reviewservice.web.dtos.ResponseInfo;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import com.errabi.reviewservice.web.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.errabi.reviewservice.utils.ReviewConstant.SYSTEM_ERROR_DESCRIPTION;
import static com.errabi.reviewservice.web.mapper.ReviewMapper.buildSuccessResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ResponseInfo saveReview(ReviewDto reviewDto) {
        log.info("Saving review {}", reviewDto);
        Review reviewToSave = reviewMapper.toEntity(reviewDto);
        reviewRepository.save(reviewToSave);
        return buildSuccessResponse(reviewDto);
    }

    public ResponseInfo updateReview(ReviewDto reviewDto) {
        log.info("Updating review {}", reviewDto);
        Review reviewToUpdate = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        reviewMapper.updateFromDto(reviewDto, reviewToUpdate);
        reviewRepository.save(reviewToUpdate);
        return buildSuccessResponse();
    }

    public void deleteReview(String id) {
        log.info("Deleting review {}", id);
        Review reviewToDelete = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(SYSTEM_ERROR_DESCRIPTION));
        reviewRepository.deleteById(reviewToDelete.getId());
        buildSuccessResponse();
    }

    public ResponseInfo getAllReviewsByProductId(Long productId, Pageable pageable) {
        log.info("Fetching all reviews by product id {}", productId);
        Page<Review> reviewPage = reviewRepository.findAllByProductId(productId, pageable);
        if (!reviewPage.isEmpty()) {
            return buildSuccessResponse(reviewPage.map(reviewMapper::toDto));
        } else {
            return buildSuccessResponse(Page.empty());
        }
    }
}
