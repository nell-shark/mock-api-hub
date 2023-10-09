package com.nellshark.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nellshark.models.Comment;
import com.nellshark.models.Post;
import com.nellshark.services.PostService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
class PostControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private PostService postService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";
    when(postService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/posts")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(postService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(postService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = objectMapper.writeValueAsString(entity);
    when(postService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/posts/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(postService).getEntityById(entity.getId());
    verifyNoMoreInteractions(postService);
  }

  @Test
  void testPostEntity() throws Exception {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/posts")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/posts/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/posts/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/posts/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetCommentsByPostId() throws Exception {
    Comment comment = new Comment(1L, "Text", 1L, 1L);
    List<Comment> comments = List.of(comment);

    Post entity = new Post(
        1L,
        "Title",
        "Content",
        LocalDateTime.now(),
        1L
    );

    String json = objectMapper.writeValueAsString(comments);
    when(postService.getCommentsByPostId(entity.getId(), Collections.emptyMap()))
        .thenReturn(comments);

    mockMvc.perform(get("/api/v1/posts/{id}/comments", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(postService).getCommentsByPostId(entity.getId(), Collections.emptyMap());
    verifyNoMoreInteractions(postService);
  }
}
