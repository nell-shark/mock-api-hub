package com.nellshark.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FaviconController.class)
class FaviconControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testReturnNoFavicon() throws Exception {
    mockMvc.perform(get("/favicon.ico")
            .contentType(APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
