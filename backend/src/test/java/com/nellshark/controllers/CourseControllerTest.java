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
import com.nellshark.models.Course;
import com.nellshark.services.CourseService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CourseService courseService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Course entity = new Course(
        1L,
        "Name",
        "Description",
        99.99,
        "Instructor",
        "Duartion",
        LocalDate.now(),
        LocalDate.now()
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(courseService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/courses")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(courseService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(courseService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Course entity = new Course(
        1L,
        "Name",
        "Description",
        99.99,
        "Instructor",
        "Duartion",
        LocalDate.now(),
        LocalDate.now()
    );

    String json = objectMapper.writeValueAsString(entity);

    when(courseService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/courses/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(courseService).getEntityById(entity.getId());
    verifyNoMoreInteractions(courseService);
  }

  @Test
  void testPostEntity() throws Exception {
    Course entity = new Course(
        1L,
        "Name",
        "Description",
        99.99,
        "Instructor",
        "Duartion",
        LocalDate.now(),
        LocalDate.now()
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/courses")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Course entity = new Course(
        1L,
        "Name",
        "Description",
        99.99,
        "Instructor",
        "Duartion",
        LocalDate.now(),
        LocalDate.now()
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/courses/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Course entity = new Course(
        1L,
        "Name",
        "Description",
        99.99,
        "Instructor",
        "Duartion",
        LocalDate.now(),
        LocalDate.now()
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/courses/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/courses/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
