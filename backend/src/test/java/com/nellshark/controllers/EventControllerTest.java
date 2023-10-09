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
import com.nellshark.models.Event;
import com.nellshark.models.Speaker;
import com.nellshark.services.EventService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventController.class)
class EventControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EventService eventService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(eventService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/events")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(eventService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(eventService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    String json = objectMapper.writeValueAsString(entity);

    when(eventService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/events/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(eventService).getEntityById(entity.getId());
    verifyNoMoreInteractions(eventService);
  }

  @Test
  void testPostEntity() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/events")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/events/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        List.of(speaker)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/events/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/events/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetSpeakersByEventId() throws Exception {
    Speaker speaker = new Speaker(
        "Name",
        "Topic",
        "bio"
    );

    List<Speaker> speakers = List.of(speaker);

    Event entity = new Event(
        1L,
        "Title",
        LocalDate.now(),
        "Location",
        "Description",
        speakers
    );

    String json = objectMapper.writeValueAsString(speakers);
    when(eventService.getSpeakersByEventId(entity.getId())).thenReturn(speakers);

    mockMvc.perform(get("/api/v1/events/{id}/speakers", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(eventService).getSpeakersByEventId(entity.getId());
    verifyNoMoreInteractions(eventService);
  }
}
