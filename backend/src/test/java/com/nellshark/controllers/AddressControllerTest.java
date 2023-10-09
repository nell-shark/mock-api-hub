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
import com.nellshark.models.Address;
import com.nellshark.services.AddressService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private AddressService addressService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(addressService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/addresses")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(addressService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(addressService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    String json = objectMapper.writeValueAsString(entity);

    when(addressService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/addresses/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(addressService).getEntityById(entity.getId());
    verifyNoMoreInteractions(addressService);
  }

  @Test
  void testPostEntity() throws Exception {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/addresses")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/addresses/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Address entity = new Address(
        1L,
        "Street",
        "City",
        "CountryCode",
        "Country",
        "PostCode"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/addresses/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/addresses/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
