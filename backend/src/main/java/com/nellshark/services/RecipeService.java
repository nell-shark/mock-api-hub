package com.nellshark.services;

import com.nellshark.models.Recipe;
import com.nellshark.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends AbstractGenericService<Recipe> {

  public RecipeService(RecipeRepository recipeRepository) {
    super(recipeRepository);
  }
}
