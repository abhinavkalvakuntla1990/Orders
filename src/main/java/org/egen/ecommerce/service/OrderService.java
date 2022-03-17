package org.egen.ecommerce.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egen.ecommerce.domain.entities.Address;
import org.egen.ecommerce.domain.entities.Item;
import org.egen.ecommerce.domain.entities.Order;
import org.egen.ecommerce.domain.entities.Payment;
import org.egen.ecommerce.domain.repository.OrderRepository;
import org.egen.ecommerce.dto.OrderDto;
import org.egen.ecommerce.enums.OrderStatusEnum;
import org.egen.ecommerce.web.exception.custom.PreconditionFailedException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ModelMapper modelMapper;
  private static final String ERROR_MESSAGE = "Failed to {} order";

  public OrderDto getOrderById(String orderId) {
    log.debug("Get order by orderID: " + orderId);
    Order order = orderRepository.findByOrderId(orderId)
          .orElseThrow(() -> new DataRetrievalFailureException("Could not find Order  with order ID: " + orderId));

    return modelMapper.map(order, OrderDto.class);
  }

  @Transactional
  public OrderDto createOrder(OrderDto orderDto) {
    log.debug("Create order for the request {} " + orderDto);
    String errorMessage = ERROR_MESSAGE.replace("{}", "create");

    validateMandatoryFields(orderDto, errorMessage);

    Order orderToBeSaved = Order.builder()
          .orderId(UUID.randomUUID().toString())
          .status(OrderStatusEnum.ONGOING)
          .customerId(orderDto.getCustomerId())
          .deliveryMethod(orderDto.getDeliveryMethod())
          .subTotal(orderDto.getSubTotal())
          .tax(orderDto.getTax())
          .shippingCharges(orderDto.getShippingCharges() != null ? orderDto.getShippingCharges() : 0.0)
          .total(orderDto.getTotal())
          .payments(orderDto.getPayments().stream().map(paymentDto -> modelMapper.map(paymentDto, Payment.class)).collect(
                Collectors.toSet()))
          .shippingAddress(orderDto.getShippingAddress() != null ? modelMapper.map(orderDto.getShippingAddress(), Address.class) : null)
          .items(orderDto.getItems().stream().map(itemDto -> modelMapper.map(itemDto, Item.class)).collect(
                Collectors.toSet()))
          .build();

    Order orderSaved = orderRepository.save(orderToBeSaved);
    log.info("Created order for customer with ID: {}", orderSaved.getCustomerId());

    return modelMapper.map(orderSaved, OrderDto.class);

  }


  @Transactional
  public OrderDto cancelOrder(String orderId) {
    log.debug("Cancel order for order Id {} " + orderId);
    String errorMessage = ERROR_MESSAGE.replace("{}", "cancel");

    Order orderToBeUpdated = orderRepository.findByOrderId(orderId)
          .orElseThrow(() -> new DataRetrievalFailureException("Could not find Order  with order ID: " + orderId));

    validateCancelOrder(orderToBeUpdated.getStatus(), errorMessage);

    orderToBeUpdated.setStatus(OrderStatusEnum.CANCELLED);
    Order orderUpdated = orderRepository.save(orderToBeUpdated);

    log.info("Canceled order with ID {} for customer with ID: {}", orderUpdated.getOrderId(), orderUpdated.getCustomerId());
    return modelMapper.map(orderUpdated, OrderDto.class);
  }

  private void validateCancelOrder(OrderStatusEnum status, String errorMessage) {
    if (status.equals(OrderStatusEnum.COMPLETED)) {
      throw new DataIntegrityViolationException("Order has already been completed. " + errorMessage);
    }

    if (status.equals(OrderStatusEnum.CANCELLED)) {
      throw new DataIntegrityViolationException("Order has already been cancelled. " + errorMessage);
    }
  }

  private void validateMandatoryFields(OrderDto orderDto, String message) {
    if (isMissingMandatoryFields(orderDto)) {
      throw new DataIntegrityViolationException("Missing mandatory information. " + message);
    }
  }

  private boolean isMissingMandatoryFields(OrderDto orderDto) {
    return isTotalEmpty(orderDto) || orderDto.getCustomerId() == null ||
          orderDto.getItems() == null || orderDto.getPayments() == null;
  }

  private boolean isTotalEmpty(OrderDto orderDto) {
    return orderDto.getSubTotal() == null || orderDto.getTotal() == null || orderDto.getTax() == null;
  }
}


