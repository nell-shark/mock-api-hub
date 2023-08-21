package com.nellshark.repositories;

import com.nellshark.models.Comment;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository extends AbstractGenericRepository<Comment> {

  public CommentRepository() {
    super(List.of(new Comment(1L)));
  }
}
