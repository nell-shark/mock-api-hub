package com.nellshark.controllers;

import com.nellshark.models.Review;
import com.nellshark.services.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController extends AbstractGenericController<Review> {

  public ReviewController(ReviewService service) {
    super(service);
  }
}
