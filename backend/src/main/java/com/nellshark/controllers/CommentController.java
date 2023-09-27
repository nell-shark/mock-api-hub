package com.nellshark.controllers;

import com.nellshark.models.Comment;
import com.nellshark.services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController extends AbstractGenericController<Comment, Long> {

  public CommentController(CommentService commentService) {
    super(commentService);
  }
}
