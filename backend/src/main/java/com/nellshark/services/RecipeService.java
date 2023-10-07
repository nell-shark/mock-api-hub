package com.nellshark.services;

import com.nellshark.models.Ingredient;
import com.nellshark.models.Recipe;
import com.nellshark.repositories.RecipeRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends AbstractGenericService<Recipe, Long> {

  private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

  public RecipeService(RecipeRepository recipeRepository) {
    super(recipeRepository);
  }

  public List<Ingredient> getIngredientsByRecipeId(Long recipeId) {
    logger.info("Getting ingredients by recipe id: {}", recipeId);
    return getEntityById(recipeId).getIngredients();
  }
}
