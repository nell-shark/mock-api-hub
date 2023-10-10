package com.nellshark.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.nellshark.models.Ingredient;
import com.nellshark.models.Recipe;
import com.nellshark.repositories.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

  @Mock
  private RecipeRepository recipeRepository;

  private RecipeService underTest;

  @BeforeEach
  void setUp() {
    underTest = new RecipeService(recipeRepository);
  }

  @Test
  void testGetEntityById() {
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

    when(recipeRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    Recipe result = underTest.getEntityById(entity.getId());

    assertEquals(entity, result);
    verify(recipeRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntityByIdNotFound() {
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

    when(recipeRepository.findById(eq(entity.getId()))).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> underTest.getEntityById(entity.getId()));

    verify(recipeRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWithDefaultParams() {
    Map<String, String> filterParams = new HashMap<>();
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWithFilterParams() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "name");
    filterParams.put("page", "1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "name");
    Pageable pageable = PageRequest.of(0, 10, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWhenPageIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWhenSizeIsEmpty() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("sort", "id");
    filterParams.put("page", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(9, 10, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entitPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entitPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWithInvalidPage() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "-1");
    filterParams.put("size", "10");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWithInvalidSize() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("direction", "ASC");
    filterParams.put("page", "1");
    filterParams.put("size", "-1");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, 10, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetEntitiesWithNotExistingField() {
    Map<String, String> filterParams = new HashMap<>();
    filterParams.put("notExistingField", "*");
    Sort sort = Sort.by(ASC, "id");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
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

    List<Recipe> expectedList = List.of(entity);
    Page<Recipe> entityPage = new PageImpl<>(expectedList);

    Specification<Recipe> specification = ArgumentMatchers.any();
    when(recipeRepository.findAll(specification, eq(pageable))).thenReturn(entityPage);

    List<Recipe> result = underTest.getEntities(filterParams);

    assertEquals(expectedList, result);
    verifyNoMoreInteractions(recipeRepository);
  }

  @Test
  void testGetIngredientsByRecipeId() {
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

    when(recipeRepository.findById(eq(entity.getId()))).thenReturn(Optional.of(entity));

    List<Ingredient> result = underTest.getIngredientsByRecipeId(entity.getId());

    assertEquals(ingredients, result);
    verify(recipeRepository).findById(eq(entity.getId()));
    verifyNoMoreInteractions(recipeRepository);
  }
}
