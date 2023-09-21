package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Item {

  private Long productId;
  private Long quantity;
}
