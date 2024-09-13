# Spring Boot MongoDB REST API
### Project Overview
This repository contains the code for a RESTful API built using Spring Boot and MongoDB. The project was inspired by the "Spring Boot Tutorial — Build a Rest Api with MongoDB" video available on [YouTube](https://www.youtube.com/watch?v=ssj0CGxv60k "YouTube"). The primary goal of this project is to simplify the process of querying MongoDB by taking advantage of Spring Boot's Query Methods feature.

### Getting Started
1. To get started with this project, follow these steps:
2. Clone this repository to your local machine.
3. Ensure that you have MongoDB installed and running on your system.
4. Configure your MongoDB connection settings in the application.properties file.
5. Build and run the Spring Boot application.
6. Explore the available REST API endpoints to perform various CRUD operations on student data.

### Key Features
- **Query Methods**: I've implemented methods that allow querying MongoDB in a straightforward manner. For instance, you can find students by email without having to manually construct complex queries.
- **MongoDB Operations**: The tutorial covers various MongoDB operations, including raw queries, document deletion, and verifying database and collection functionality.
- **Repository Integration**: I demonstrate how to seamlessly integrate a repository into a service class to retrieve student data using the findAll method.
- **Lombok Integration**: Lombok has been incorporated to minimize boilerplate code, resulting in a cleaner and more maintainable codebase.