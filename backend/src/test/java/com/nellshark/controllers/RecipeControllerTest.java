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
import com.nellshark.models.Ingredient;
import com.nellshark.models.Recipe;
import com.nellshark.services.RecipeService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private RecipeService recipeService;

  @Test
  void testGetEntities() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        List.of(ingredient)
    );

    String json = "[" + objectMapper.writeValueAsString(entity) + "]";

    when(recipeService.getEntities(Collections.emptyMap())).thenReturn(List.of(entity));

    mockMvc.perform(get("/api/v1/recipes")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(recipeService).getEntities(Collections.emptyMap());
    verifyNoMoreInteractions(recipeService);
  }


  @Test
  void testGetEntityById() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        List.of(ingredient)
    );

    String json = objectMapper.writeValueAsString(entity);

    when(recipeService.getEntityById(eq(entity.getId()))).thenReturn(entity);

    mockMvc.perform(get("/api/v1/recipes/{id}", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(recipeService).getEntityById(eq(entity.getId()));
    verifyNoMoreInteractions(recipeService);
  }

  @Test
  void testPostEntity() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        List.of(ingredient)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(post("/api/v1/recipes")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPutEntity() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        List.of(ingredient)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(put("/api/v1/recipes/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testPatchEntity() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        List.of(ingredient)
    );

    String json = objectMapper.writeValueAsString(entity);

    mockMvc.perform(patch("/api/v1/recipes/{id}", entity.getId())
            .contentType(APPLICATION_JSON)
            .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  void testDeleteEntity() throws Exception {
    mockMvc.perform(delete("/api/v1/recipes/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetIngredientsByRecipeId() throws Exception {
    Ingredient ingredient = new Ingredient("IngredientName", "Quantity");
    List<Ingredient> ingredients = List.of(ingredient);
    Recipe entity = new Recipe(
        1L,
        "Name",
        "Instructions",
        "prepTime",
        "CookTime",
        "TotalTime",
        ingredients
    );

    String json = objectMapper.writeValueAsString(ingredients);
    when(recipeService.getIngredientsByRecipeId(entity.getId())).thenReturn(ingredients);

    mockMvc.perform(get("/api/v1/recipes/{id}/ingredients", entity.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(json));

    verify(recipeService).getIngredientsByRecipeId(entity.getId());
    verifyNoMoreInteractions(recipeService);
  }
}
