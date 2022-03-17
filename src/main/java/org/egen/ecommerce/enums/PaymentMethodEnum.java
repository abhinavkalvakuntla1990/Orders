package org.egen.ecommerce.enums;

public enum PaymentMethodEnum {

  CREDIT_CARD("Credit Card"),
  DEBIT_CARD("Debit Card"),
  CASH("Cash");

  public final String description;

  PaymentMethodEnum(final String description) {
    this.description = description;
  }
}
