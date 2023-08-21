package com.nellshark.repositories;

import com.nellshark.models.Post;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository extends AbstractGenericRepository<Post> {

  public PostRepository() {
    super(List.of());
  }
}
