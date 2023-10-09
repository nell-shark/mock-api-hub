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
import com.nellshark.models.Employee;
import com.nellshark.services.EmployeeService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EmployeeService employeeService;

  @Test
  void testGetEntities() throws Exception {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(employeeService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/employees")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(employeeService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(employeeService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    String json = objectMapper.writeValueAsString(entity);

    when(employeeService.getEntityById(eq(entity.getId()))).thenReturn(entity);

    mockMvc.perform(get("/api/v1/employees/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(employeeService).getEntityById(eq(entity.getId()));
    verifyNoMoreInteractions(employeeService);
  }

  @Test
  void testPostEntity() throws Exception {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/employees")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/employees/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Employee entity = new Employee(
        1L,
        "FisrtName",
        "LastName",
        1999L,
        "email@google.com",
        "Department",
        "Postion"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/employees/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/employees/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
