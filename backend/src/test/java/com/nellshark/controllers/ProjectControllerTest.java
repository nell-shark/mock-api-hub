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
import com.nellshark.models.Project;
import com.nellshark.models.Team;
import com.nellshark.services.ProjectService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProjectService projectService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(projectService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/projects")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(projectService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(projectService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    String json = objectMapper.writeValueAsString(entity);

    when(projectService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/projects/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(projectService).getEntityById(entity.getId());
    verifyNoMoreInteractions(projectService);
  }

  @Test
  void testPostEntity() throws Exception {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/projects")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/projects/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Team team = new Team("Name", "Role");
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        List.of(team)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/projects/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/projects/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetTeamByProjectId() throws Exception {
    List<Team> team = List.of(new Team("Name", "Role"));
    Project entity = new Project(
        1L,
        "Name",
        "Description",
        "Status",
        LocalDate.now(),
        LocalDate.now(),
        team
    );

    String json = objectMapper.writeValueAsString(team);
    when(projectService.getTeamByProjectId(entity.getId())).thenReturn(team);

    mockMvc.perform(get("/api/v1/projects/{id}/team", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(projectService).getTeamByProjectId(entity.getId());
    verifyNoMoreInteractions(projectService);
  }
}
