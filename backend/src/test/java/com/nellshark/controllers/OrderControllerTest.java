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
import com.nellshark.models.Item;
import com.nellshark.models.Order;
import com.nellshark.services.OrderService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private OrderService orderService;

  @Test
  void testGetEntities() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(orderService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/orders")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(orderService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(orderService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    String json = objectMapper.writeValueAsString(entity);

    when(orderService.getEntityById(eq(entity.getId()))).thenReturn(entity);

    mockMvc.perform(get("/api/v1/orders/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(orderService).getEntityById(eq(entity.getId()));
    verifyNoMoreInteractions(orderService);
  }

  @Test
  void testPostEntity() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/orders")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/orders/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        List.of(item)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/orders/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/orders/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetItemsByOrderId() throws Exception {
    Item item = new Item(
        1L,
        1L
    );

    List<Item> items = List.of(item);

    Order entity = new Order(
        1L,
        LocalDateTime.now(),
        "Status",
        items
    );

    String json = objectMapper.writeValueAsString(items);
    when(orderService.getItemsByOrderId(entity.getId())).thenReturn(items);

    mockMvc.perform(get("/api/v1/orders/{id}/items", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(orderService).getItemsByOrderId(entity.getId());
    verifyNoMoreInteractions(orderService);
  }
}
