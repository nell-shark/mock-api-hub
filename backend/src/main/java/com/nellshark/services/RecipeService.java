package com.nellshark.services;

import com.nellshark.exceptions.RecipeNotFoundException;
import com.nellshark.models.Recipe;
import com.nellshark.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public Recipe getRecipeById(Long id) {
    return recipeRepository
        .findById(id)
        .orElseThrow(
            () -> new RecipeNotFoundException("Recipe with id %s not found".formatted(id)));
  }
}
