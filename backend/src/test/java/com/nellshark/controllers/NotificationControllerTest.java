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
import com.nellshark.models.Notification;
import com.nellshark.services.NotificationService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private NotificationService notificationService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    String expectedJson = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(notificationService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/notifications")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(notificationService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(notificationService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    String expectedJson = objectMapper.writeValueAsString(entity);

    when(notificationService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/notifications/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(notificationService).getEntityById(entity.getId());
    verifyNoMoreInteractions(notificationService);
  }

  @Test
  void testPostEntity() throws Exception {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/notifications")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/notifications/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Notification entity = new Notification(
        1L,
        "Title",
        "Message",
        LocalDateTime.now(),
        true
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/notifications/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/notifications/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
