package com.nellshark.repositories;

import com.nellshark.models.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CustomGenericRepository<Review, Long> {

}
