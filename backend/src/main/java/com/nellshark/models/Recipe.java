package com.nellshark.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;

@Table(name = "recipes")
@Entity
public class Recipe {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, updatable = false)
  private String name;

  @Column(name = "instructions",
      nullable = false,
      updatable = false,
      columnDefinition = "VARCHAR(65535)")
  private String instructions;

  @Column(name = "prepTime", nullable = false, updatable = false)
  private String prepTime;

  @Column(name = "cookTime", nullable = false, updatable = false)
  private String cookTime;

  @Column(name = "totalTime", nullable = false, updatable = false)
  private String totalTime;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
  private List<Ingredient> ingredients;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public String getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(String prepTime) {
    this.prepTime = prepTime;
  }

  public String getCookTime() {
    return cookTime;
  }

  public void setCookTime(String cookTime) {
    this.cookTime = cookTime;
  }

  public String getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(String totalTime) {
    this.totalTime = totalTime;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "Recipe{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", instructions='" + instructions + '\'' +
        ", prepTime='" + prepTime + '\'' +
        ", cookTime='" + cookTime + '\'' +
        ", totalTime='" + totalTime + '\'' +
        ", ingredients=" + ingredients +
        '}';
  }
}
