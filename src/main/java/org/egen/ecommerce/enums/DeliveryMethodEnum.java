package org.egen.ecommerce.enums;

public enum DeliveryMethodEnum {

  IN_STORE_PICK_UP("In Store Pick Up"),
  CURB_SIDE_DELIVERY("Curb Side Delivery"),
  SHIP_TO_HOME("Ship To Home"),
  THIRD_PARTY_DELIVERY("Thrid Party Delivery");

  private final String description;

  DeliveryMethodEnum(final String description) {
  this.description = description;
  }
}
