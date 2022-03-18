package org.egen.ecommerce.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.egen.ecommerce.dto.OrderDto;
import org.egen.ecommerce.dto.UpdateOrderDto;
import org.egen.ecommerce.service.OrderService;
import org.egen.ecommerce.web.exception.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/orders")
@Tag(name="Orders")
public class OrderController {

  private final OrderService orderService;

  @Operation(summary = "Returns Order by Id")
  @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful return of order",  content = @Content(schema = @Schema(implementation = OrderDto.class))),
        @ApiResponse(responseCode = "404", description = "Unable to find order id to ",  content = @Content(schema = @Schema(implementation = ErrorInfo.class))) })
  @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
  public OrderDto getOrderById(
        @Parameter(name = "orderId", description = "Id of Order to be retrieved", required = true)
        @PathVariable String orderId) {
    return orderService.getOrderById(orderId);
  }


  @Operation(summary  = "Creates and returns a newly created Order", description = "Creates and returns a newly created Order")
  @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Returns a newly created Order", content = @Content(schema = @Schema(implementation = OrderDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorInfo.class))) })
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto create(@RequestBody @Valid OrderDto orderDto) {
    return orderService.createOrder(orderDto);
  }


  @Operation(summary = "updates order status and return updated order", description = "updates order status and returns order")
  @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns a newly updated Order", content = @Content(schema = @Schema(implementation = OrderDto.class))),
        @ApiResponse(responseCode = "412",description = "Pre Condition failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))) })
  @PatchMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public OrderDto partialUpdate(
        @PathVariable String orderId,@RequestBody  @Valid UpdateOrderDto orderDto) {
    return orderService.updateStatus(orderId, orderDto);
  }


}
