package org.egen.ecommerce.service;

import org.egen.ecommerce.domain.entities.Order;
import org.egen.ecommerce.domain.repository.OrderRepository;
import org.egen.ecommerce.dto.OrderDto;
import org.egen.ecommerce.dto.UpdateOrderDto;
import org.egen.ecommerce.enums.OrderStatusEnum;
import org.egen.ecommerce.util.OrdersDataProvider;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;

@Test(groups = "unit")
public class OrderServiceTest {

  private OrderService orderService;

  @Mock
  private OrderRepository orderRepository;

  @BeforeClass
  public void setup() {
    MockitoAnnotations.initMocks(this);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    orderService = new OrderService(orderRepository, modelMapper);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    reset(orderRepository);
  }

  @Test(description = "Get order based on the order ID")
  public void getOrder_byOrderId_shouldReturnOrder() {
    Order expectedOrder = OrdersDataProvider.getOrder();

    when(orderRepository.findByOrderId(anyString())).thenReturn(java.util.Optional.ofNullable(expectedOrder));

    OrderDto actualOrder = orderService.getOrderById(UUID.randomUUID().toString());

    assertEquals(actualOrder.getCustomerId(), expectedOrder.getCustomerId());
  }

  @Test(description = "Test fetch Order: Order does not exist",
        expectedExceptions = DataRetrievalFailureException.class)
  public void getOrder_byOrderId_returnException() {

    when(orderRepository.findByOrderId(anyString())).thenReturn(Optional.ofNullable(null));

    orderService.getOrderById(UUID.randomUUID().toString());
  }

  @Test(description = "Should be able to save order")
  public void createOrder_shouldSaveOrder() {
    OrderDto orderToBeCreated =  OrdersDataProvider.getOrderDto();

    when(orderRepository.save(any())).thenAnswer(a -> a.getArguments()[0]);
    orderService.createOrder(orderToBeCreated);

    ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

    verify(orderRepository).save(orderArgumentCaptor.capture());
  }

  @Test(description = "Should not be able save order if mandatory fields are not provided",
        expectedExceptions = DataIntegrityViolationException.class)
  public void createOrder_shouldThrowException() {
    OrderDto orderToBeCreated =  OrdersDataProvider.getOrderDto();
    orderToBeCreated.setItems(null);
    when(orderRepository.save(any())).thenAnswer(a -> a.getArguments()[0]);
    orderService.createOrder(orderToBeCreated);

    ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

    verify(orderRepository).save(orderArgumentCaptor.capture());
  }

  @Test(description = "should be able to cancel the order")
  public void cancelOrder_shouldUpdateSuccessfully() {
    Order orderTobeUpdated = OrdersDataProvider.getOrder();

    when(orderRepository.findByOrderId(anyString())).thenReturn(java.util.Optional.ofNullable(orderTobeUpdated));
    when(orderRepository.save(any())).thenAnswer(a -> a.getArguments()[0]);

    OrderDto updatedOrder = orderService.updateStatus(orderTobeUpdated.getOrderId(),
            new UpdateOrderDto().builder().status(OrderStatusEnum.CANCELLED).build());

    assertEquals(updatedOrder.getStatus(), OrderStatusEnum.CANCELLED);

  }

  @Test(description = "should not cancel the order if completed",
  expectedExceptions = DataIntegrityViolationException.class)
  public void cancelOrder_shouldThrowException_forAlreadyCompletedOrder() {
    Order orderTobeUpdated = OrdersDataProvider.getOrder();
    orderTobeUpdated.setStatus(OrderStatusEnum.CANCELLED);

    when(orderRepository.findByOrderId(anyString())).thenReturn(java.util.Optional.ofNullable(orderTobeUpdated));
    when(orderRepository.save(any())).thenAnswer(a -> a.getArguments()[0]);

    OrderDto updatedOrder = orderService.updateStatus(orderTobeUpdated.getOrderId(),
            new UpdateOrderDto().builder().status(OrderStatusEnum.CANCELLED).build());

    assertEquals(updatedOrder.getStatus(), OrderStatusEnum.CANCELLED);

  }

}
