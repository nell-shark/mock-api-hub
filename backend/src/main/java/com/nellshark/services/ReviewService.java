package com.nellshark.services;

import com.nellshark.exceptions.ReviewNotFoundException;
import com.nellshark.models.Review;
import com.nellshark.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Review getReviewById(Long id) {
    return reviewRepository
        .findById(id)
        .orElseThrow(
            () -> new ReviewNotFoundException("Review with id %s not found".formatted(id)));
  }
}
