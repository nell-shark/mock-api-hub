package com.nellshark.controllers;

import com.nellshark.models.Review;
import com.nellshark.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id) {
    Review review = reviewService.getReviewById(id);
    return ResponseEntity.ok(review);
  }
}
