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
import com.nellshark.models.Book;
import com.nellshark.services.BookService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private BookService bookService;

  @BeforeEach
  void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  void testGetEntities() throws Exception {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    String expectedJson = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(bookService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/books")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(bookService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(bookService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    String expectedJson = objectMapper.writeValueAsString(entity);

    when(bookService.getEntityById(entity.getId())).thenReturn(entity);

    mockMvc.perform(get("/api/v1/books/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));

    verify(bookService).getEntityById(entity.getId());
    verifyNoMoreInteractions(bookService);
  }

  @Test
  void testPostEntity() throws Exception {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/books")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/books/1")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Book entity = new Book(
        1L,
        "Title",
        1L,
        "Author",
        1932L,
        "Language",
        "Description"
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/books/1")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/books/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
