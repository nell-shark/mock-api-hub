package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient {

  private String ingredientName;
  private String quantity;

  public Ingredient() {
  }

  public Ingredient(String ingredientName, String quantity) {
    this.ingredientName = ingredientName;
    this.quantity = quantity;
  }

  public String getIngredientName() {
    return ingredientName;
  }

  public void setIngredientName(String ingredientName) {
    this.ingredientName = ingredientName;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Ingredient{" +
        "ingredientName='" + ingredientName + '\'' +
        ", quantity='" + quantity + '\'' +
        '}';
  }
}
