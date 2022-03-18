package org.egen.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egen.ecommerce.enums.DeliveryMethodEnum;
import org.egen.ecommerce.enums.OrderStatusEnum;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
  private String orderId;
  private OrderStatusEnum status;
  private Long customerId;
  private Double subTotal;
  private Double tax;
  private Double shippingCharges;
  private Double total;
  private AddressDto shippingAddress;
  private Set<PaymentDto> payments;
  private Set<ItemDto> items;
  private DeliveryMethodEnum deliveryMethod;
  private Instant completedAt;
  private Instant cancelledAt;
}
