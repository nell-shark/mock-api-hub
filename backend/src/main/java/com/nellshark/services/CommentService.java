package com.nellshark.services;

import com.nellshark.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }
}
