package com.nellshark.controllers;

import com.nellshark.models.Comment;
import com.nellshark.models.Review;
import com.nellshark.models.User;
import com.nellshark.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractGenericController<User, Long> {

  private final UserService userService;

  public UserController(UserService userService) {
    super(userService);
    this.userService = userService;
  }

  @GetMapping("/{id}/comments")
  public ResponseEntity<List<Comment>> getCommentsByUserId(
      @PathVariable("id") Long userId,
      Map<String, String> filterParams
  ) {
    List<Comment> comments = userService.getCommentsByUserId(userId, filterParams);
    return ResponseEntity.ok(comments);
  }

  @GetMapping("/{id}/reviews")
  public ResponseEntity<List<Review>> getReviewsByUserId(
      @PathVariable("id") Long userId,
      Map<String, String> filterParams
  ) {
    List<Review> reviews = userService.getReviewsByUserId(userId, filterParams);
    return ResponseEntity.ok(reviews);
  }
}
