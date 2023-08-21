package com.nellshark.services;

import com.nellshark.models.Post;
import com.nellshark.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService extends AbstractGenericService<Post> {

  public PostService(PostRepository postRepository) {
    super(postRepository);
  }
}
