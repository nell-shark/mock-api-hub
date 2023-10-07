package com.nellshark.repositories;

import com.nellshark.models.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CustomGenericRepository<Comment, Long> {

}
