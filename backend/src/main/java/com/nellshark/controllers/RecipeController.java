package com.nellshark.controllers;

import com.nellshark.models.Ingredient;
import com.nellshark.models.Recipe;
import com.nellshark.services.RecipeService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController extends AbstractGenericController<Recipe, Long> {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    super(recipeService);
    this.recipeService = recipeService;
  }

  @GetMapping("/{id}/ingredients")
  public ResponseEntity<List<Ingredient>> getIngredientsByRecipeId(
      @PathVariable("id") Long recipeId) {
    List<Ingredient> ingredients = recipeService.getIngredientsByRecipeId(recipeId);
    return ResponseEntity.ok(ingredients);
  }
}
