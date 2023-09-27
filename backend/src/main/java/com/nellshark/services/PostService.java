package com.nellshark.services;

import com.nellshark.exceptions.PostNotFoundException;
import com.nellshark.models.Post;
import com.nellshark.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Post getPostById(Long id) {
    return postRepository
        .findById(id)
        .orElseThrow(() -> new PostNotFoundException("Post with id %s not found".formatted(id)));
  }
}
