package com.nellshark.services;

import com.nellshark.models.Review;
import com.nellshark.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractGenericService<Review> {

  public ReviewService(ReviewRepository reviewRepository) {
    super(reviewRepository);
  }
}
