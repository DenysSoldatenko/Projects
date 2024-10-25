//package com.example.stockservice.services;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class OrderConsumer {
//
//  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//  public void consume(OrderEvent event) {
//    log.info(String.format("Order event received in stock service => %s", event.toString()));
//  }
//}