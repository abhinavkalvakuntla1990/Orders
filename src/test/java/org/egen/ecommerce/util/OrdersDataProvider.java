package org.egen.ecommerce.util;

import org.egen.ecommerce.domain.entities.Item;
import org.egen.ecommerce.domain.entities.Order;
import org.egen.ecommerce.domain.entities.Payment;
import org.egen.ecommerce.dto.ItemDto;
import org.egen.ecommerce.dto.OrderDto;
import org.egen.ecommerce.dto.PaymentDto;
import org.egen.ecommerce.enums.DeliveryMethodEnum;
import org.egen.ecommerce.enums.ItemStatusEnum;
import org.egen.ecommerce.enums.OrderStatusEnum;
import org.egen.ecommerce.enums.PaymentMethodEnum;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public class OrdersDataProvider {


  public static Order getOrder() {

    Payment payment = new Payment().builder()
          .paymentDate(Instant.now())
          .paymentMethod(PaymentMethodEnum.DEBIT_CARD)
          .confirmationNumber(1234556l)
          .build();

    Item item = new Item().builder()
          .name("Man utd Jersey")
          .status(ItemStatusEnum.READY_FOR_DELIVERY)
          .quantity(2)
          .build();

    Order order = new Order().builder()
          .orderId(UUID.randomUUID().toString())
          .status(OrderStatusEnum.ONGOING)
          .customerId(1223457L)
          .subTotal(150.0)
          .tax(20.0)
          .total(170.0)
          .items(new HashSet<>(Arrays.asList(item)))
          .deliveryMethod(DeliveryMethodEnum.IN_STORE_PICK_UP)
          .payments(new HashSet<>(Arrays.asList(payment)))
                .build();

    return order;
  }

  public static OrderDto getOrderDto() {

    PaymentDto payment = new PaymentDto().builder()
          .paymentDate(Instant.now())
          .paymentMethod(PaymentMethodEnum.DEBIT_CARD)
          .confirmationNumber(1234556l)
          .build();

    ItemDto item = new ItemDto().builder()
          .name("Man utd Jersey")
          .status(ItemStatusEnum.READY_FOR_DELIVERY)
          .quantity(2)
          .build();

    OrderDto orderDto = new OrderDto().builder()
          .status(OrderStatusEnum.ONGOING)
          .customerId(1223457L)
          .subTotal(150.0)
          .tax(20.0)
          .total(170.0)
          .items(new HashSet<>(Arrays.asList(item)))
          .deliveryMethod(DeliveryMethodEnum.IN_STORE_PICK_UP)
          .payments(new HashSet<>(Arrays.asList(payment)))
          .build();

    return orderDto;
  }
}
