package com.nellshark.services;

import com.nellshark.models.Comment;
import com.nellshark.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends AbstractGenericService<Comment> {

  public CommentService(CommentRepository commentRepository) {
    super(commentRepository);
  }
}
