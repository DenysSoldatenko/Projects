package com.example.orderservice.controllers;

import com.example.orderservice.services.OrderProducer;
import com.example.shareddomain.models.Order;
import com.example.shareddomain.models.OrderEvent;
import com.example.shareddomain.utils.OrderEventFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing orders.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Order Controller", description = "REST controller for managing orders")
public class OrderController {

  private final OrderProducer orderProducer;

  /**
   * Places a new order.
   *
   * @param order the order to be placed
   * @return a success message
   */
  @PostMapping()
  @Operation(
      summary = "Place a new order",
      description = "This endpoint places a new order and sends it to the messaging system"
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Order placed successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public String placeOrder(@RequestBody Order order) {
    OrderEvent orderEvent = OrderEventFactory.createOrderEvent(order);
    orderProducer.sendMessage(orderEvent);

    return String.format("Order with ID %s placed successfully. Current status: %s.",
      orderEvent.orderDetails().id(), orderEvent.status());
  }
}
