package com.nellshark.repositories;

import com.nellshark.models.Recipe;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeRepository extends AbstractGenericRepository<Recipe> {

  public RecipeRepository() {
    super(List.of());
  }
}
