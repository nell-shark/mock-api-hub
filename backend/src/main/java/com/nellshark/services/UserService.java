package com.nellshark.services;

import com.nellshark.models.Comment;
import com.nellshark.models.Review;
import com.nellshark.models.User;
import com.nellshark.repositories.UserRepository;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractGenericService<User, Long> {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final CommentService commentService;
  private final ReviewService reviewService;

  public UserService(UserRepository userRepository,
      CommentService commentService,
      ReviewService reviewService) {
    super(userRepository);
    this.commentService = commentService;
    this.reviewService = reviewService;
  }

  public List<Comment> getCommentsByUserId(Long userId, Map<String, String> filterParams) {
    logger.info("Getting comments by user id: {}, filterParams={}", userId, filterParams);
    filterParams.put("id", String.valueOf(userId));
    return commentService.getEntities(filterParams);
  }

  public List<Review> getReviewsByUserId(Long userId, Map<String, String> filterParams) {
    logger.info("Getting reviews by user id: {}, filterParams={}", userId, filterParams);
    filterParams.put("id", String.valueOf(userId));
    return reviewService.getEntities(filterParams);
  }
}
