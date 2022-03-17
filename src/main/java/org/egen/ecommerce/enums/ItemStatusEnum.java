package org.egen.ecommerce.enums;

public enum ItemStatusEnum {

  IN_TRANSIT("In Transit"),
  READY_FOR_DELIVERY("Ready for Delivery"),
  DELIVERED("Delivered");

  public final String description;

  ItemStatusEnum(final String description) {

  this.description = description;

  }
}
