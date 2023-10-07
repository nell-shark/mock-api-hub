package com.nellshark.repositories;

import com.nellshark.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CustomGenericRepository<Post, Long> {

}
