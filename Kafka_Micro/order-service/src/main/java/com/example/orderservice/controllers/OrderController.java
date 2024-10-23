package com.example.orderservice.controllers;

import com.example.orderservice.services.OrderProducer;
import com.example.shareddomain.models.Order;
import com.example.shareddomain.models.OrderEvent;
import com.example.shareddomain.utils.OrderEventFactory;
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
public class OrderController {

  private final OrderProducer orderProducer;

  /**
   * Places a new order.
   *
   * @param order the order to be placed
   * @return a success message
   */
  @PostMapping()
  public String placeOrder(@RequestBody Order order) {
    OrderEvent orderEvent = OrderEventFactory.createOrderEvent(order);
    orderProducer.sendMessage(orderEvent);

    return String.format("Order with ID %s placed successfully. Current status: %s.",
      orderEvent.orderDetails().id(), orderEvent.status());
  }
}
