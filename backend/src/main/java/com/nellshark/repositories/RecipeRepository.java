package com.nellshark.repositories;

import com.nellshark.models.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CustomGenericRepository<Recipe, Long> {

}
