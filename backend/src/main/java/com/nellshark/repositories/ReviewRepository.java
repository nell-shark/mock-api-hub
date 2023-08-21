package com.nellshark.repositories;

import com.nellshark.models.Review;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends AbstractGenericRepository<Review> {

  public ReviewRepository() {
    super(List.of());
  }
}
