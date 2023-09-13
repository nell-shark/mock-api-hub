package com.nellshark.services;

import com.nellshark.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }
}
