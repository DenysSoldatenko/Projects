# Kafka_Micro Project
A microservices architecture using Kafka for asynchronous communication among services. This project includes the following components:

- **Email Service**: Consumes order events from Kafka to send email notifications.
- **Order Service**: Manages order placement and produces order events to Kafka.
- **Stock Service**: Consumes order events to manage stock levels.
- **Shared Domain**: Contains shared models and utilities for inter-service communication.

## Features
- RESTful API for order management with Swagger documentation.
- Asynchronous event-driven communication using Apache Kafka.
- Comprehensive logging for monitoring service interactions.

## Getting Started
To run this project, ensure you have Kafka set up and configured. Each service can be run independently and communicates through Kafka topics.