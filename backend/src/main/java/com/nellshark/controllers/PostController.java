package com.nellshark.controllers;

import com.nellshark.models.Comment;
import com.nellshark.models.Post;
import com.nellshark.services.PostService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController extends AbstractGenericController<Post, Long> {

  private final PostService postService;

  public PostController(PostService postService) {
    super(postService);
    this.postService = postService;
  }

  @GetMapping("/{id}/comments")
  public ResponseEntity<List<Comment>> getCommentsByPostId(
      @PathVariable("id") Long postId,
      Map<String, String> filterParams
  ) {
    List<Comment> comments = postService.getCommentsByPostId(postId, filterParams);
    return ResponseEntity.ok(comments);
  }
}
