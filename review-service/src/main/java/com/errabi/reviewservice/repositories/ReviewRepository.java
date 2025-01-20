package com.errabi.reviewservice.repositories;

import com.errabi.reviewservice.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Page<Review> findAllByProductId(Long productId, Pageable pageable);
}
