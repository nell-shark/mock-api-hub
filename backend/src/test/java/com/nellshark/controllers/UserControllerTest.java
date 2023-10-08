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
import com.nellshark.models.User;
import com.nellshark.services.UserService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

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

    String expectedJson = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(userService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/users")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

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

    String expectedJson = objectMapper.writeValueAsString(entity);

    when(userService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/users/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(userService).getEntityById(entity.getId());
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
}
