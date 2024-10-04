# Kafka Message API
This repository provides a simple REST API for publishing messages to Kafka topics. It includes two controllers: one for sending messages in JSON format and another for sending plain text messages.

## Features
- **Publish JSON Messages**:
    - Allows users to send structured product information in JSON format, making it easy to integrate with systems that require detailed product data.
- **Send Messages via Request Body**:
    - Users can send plain text messages directly in the request body, facilitating quick and straightforward message publication.
- **Send Messages via Query Parameter**:
    - Provides flexibility by allowing messages to be sent as query parameters in GET requests, which can be useful for quick tests or integration with other systems.
- **Swagger Documentation**:
    - Automatically generated API documentation via Swagger (OpenAPI) for easy reference and testing of endpoints.
