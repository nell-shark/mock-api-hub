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
import com.nellshark.models.Dimension;
import com.nellshark.models.Product;
import com.nellshark.services.ProductService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductService productService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    String expectedJson = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(productService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/products")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(productService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(productService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    String expectedJson = objectMapper.writeValueAsString(entity);

    when(productService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/products/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(productService).getEntityById(entity.getId());
    verifyNoMoreInteractions(productService);
  }

  @Test
  void testPostEntity() throws Exception {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/products")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/products/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Dimension dimension = new Dimension(1.0, 2.0, 3.0);
    Product entity = new Product(
        1L,
        "Name",
        "Description",
        99.99,
        "Currency",
        "Manufacturer",
        "Category",
        true,
        dimension
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/products/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/products/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}

