package com.nellshark.controllers;

import com.nellshark.models.Post;
import com.nellshark.services.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController extends AbstractGenericController<Post> {

  public PostController(PostService service) {
    super(service);
  }
}
