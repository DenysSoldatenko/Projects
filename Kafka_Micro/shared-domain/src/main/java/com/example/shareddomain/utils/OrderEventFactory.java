package com.example.shareddomain.utils;

import static java.util.UUID.randomUUID;

import com.example.shareddomain.models.Order;
import com.example.shareddomain.models.OrderEvent;
import lombok.experimental.UtilityClass;

/**
 * Factory class for creating OrderEvent instances.
 */
@UtilityClass
public class OrderEventFactory {

  /**
   * Creates an OrderEvent based on the provided Order.
   *
   * @param order the order for which the event is created
   * @return an OrderEvent instance
   */
  public static OrderEvent createOrderEvent(Order order) {
    Order newOrder = new Order(randomUUID().toString(), order.itemName(), order.quantity(), order.totalPrice());
    return new OrderEvent("PENDING", "Order status is in pending state", newOrder);
  }
}
