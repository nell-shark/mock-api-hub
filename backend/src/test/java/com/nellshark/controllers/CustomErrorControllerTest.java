package com.nellshark.controllers;

import static jakarta.servlet.RequestDispatcher.ERROR_REQUEST_URI;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nellshark.exceptions.HandlerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomErrorController.class)
class CustomErrorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testHandleError() throws Exception {
    String errorRequestUri = "/test/error";
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getAttribute(ERROR_REQUEST_URI)).thenReturn(errorRequestUri);

    mockMvc.perform(get("/error"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(
            result -> assertTrue(result.getResolvedException() instanceof HandlerNotFoundException)
        );
  }
}
