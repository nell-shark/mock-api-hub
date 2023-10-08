package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Item {

  private Long productId;
  private Long quantity;

  public Item() {
  }

  public Item(Long productId, Long quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Item{" +
        "productId=" + productId +
        ", quantity=" + quantity +
        '}';
  }
}
