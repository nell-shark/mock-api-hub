package com.nellshark.controllers;

import static org.mockito.ArgumentMatchers.eq;
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
import com.nellshark.models.Comment;
import com.nellshark.models.Review;
import com.nellshark.models.User;
import com.nellshark.services.UserService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userService;

  @Test
  void testGetEntities() throws Exception {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(userService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/users")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(userService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(userService);
  }


  @Test
  void testGetEntityById() throws Exception {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(entity);

    when(userService.getEntityById(eq(entity.getId()))).thenReturn(entity);

    mockMvc.perform(get("/api/v1/users/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(userService).getEntityById(eq(entity.getId()));
    verifyNoMoreInteractions(userService);
  }

  @Test
  void testPostEntity() throws Exception {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/users")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/users/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/users/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/users/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetCommentsByUserId() throws Exception {
    Comment comment = new Comment(1L, "Text", 1L, 1L);
    List<Comment> comments = List.of(comment);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(comments);
    when(userService.getCommentsByUserId(entity.getId(), Collections.emptyMap()))
        .thenReturn(comments);

    mockMvc.perform(get("/api/v1/users/{id}/comments", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(userService).getCommentsByUserId(entity.getId(), Collections.emptyMap());
    verifyNoMoreInteractions(userService);
  }

  @Test
  void testGetReviewsByUserId() throws Exception {
    Review review = new Review(
        1L, 1L, 1L, 1L, LocalDate.now(), "Body", 1L
    );
    List<Review> reviews = List.of(review);
    User entity = new User(
        1L,
        "Username",
        "email@google.com",
        "password",
        18L,
        "Location"
    );

    String json = objectMapper.writeValueAsString(reviews);
    when(userService.getReviewsByUserId(entity.getId(), Collections.emptyMap()))
        .thenReturn(reviews);

    mockMvc.perform(get("/api/v1/users/{id}/reviews", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(userService).getReviewsByUserId(entity.getId(), Collections.emptyMap());
    verifyNoMoreInteractions(userService);
  }
}
