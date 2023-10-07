package com.nellshark.services;

import com.nellshark.models.Comment;
import com.nellshark.models.Post;
import com.nellshark.repositories.PostRepository;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostService extends AbstractGenericService<Post, Long> {

  private static final Logger logger = LoggerFactory.getLogger(PostService.class);

  private final CommentService commentService;

  public PostService(PostRepository postRepository, CommentService commentService) {
    super(postRepository);
    this.commentService = commentService;
  }

  public List<Comment> getCommentsByPostId(Long postId, Map<String, String> filterParams) {
    logger.info("Getting comments by post id: {}, filterParams={}", postId, filterParams);
    filterParams.put("id", String.valueOf(postId));
    return commentService.getEntities(filterParams);
  }
}
