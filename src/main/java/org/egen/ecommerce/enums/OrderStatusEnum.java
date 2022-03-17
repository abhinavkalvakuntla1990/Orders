package org.egen.ecommerce.enums;

public enum OrderStatusEnum {

ONGOING("Ongoing"),
CANCELLED("Cancelled"),
COMPLETED("Completed");

private String description;

  OrderStatusEnum(final String description) {
  this.description = description;
  }
}
