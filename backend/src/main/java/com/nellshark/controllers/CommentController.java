package com.nellshark.controllers;

import com.nellshark.models.Comment;
import com.nellshark.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
    Comment comment = commentService.getCommentById(id);
    return ResponseEntity.ok(comment);
  }
}
