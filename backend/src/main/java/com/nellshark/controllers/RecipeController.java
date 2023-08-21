package com.nellshark.controllers;

import com.nellshark.models.Recipe;
import com.nellshark.services.RecipeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController extends AbstractGenericController<Recipe> {

  public RecipeController(RecipeService service) {
    super(service);
  }
}
