package com.nellshark.services;

import com.nellshark.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeService(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }
}
