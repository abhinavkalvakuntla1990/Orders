package org.egen.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egen.ecommerce.domain.entities.Order;
import org.egen.ecommerce.enums.PaymentMethodEnum;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
  private PaymentMethodEnum paymentMethod;

  private AddressDto billingAddress;

  private Long confirmationNumber;

  private Instant paymentDate;
}
