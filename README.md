# Shopping Application - Spring Boot and Microservices
Welcome to my Shopping Application! This project showcases my experience in Java, Spring, Spring Boot, and microservices architecture. The application is designed to handle various aspects of online shopping.

## Overview
The Shopping Application is currently composed of three key services:

**Inventory Service**: Responsible for managing product inventory.  
**Order Service**: Handles order processing and management.  
**Product Service**: Manages product-related data and interactions.  
Additionally, I've implemented a discovery server using Netflix Eureka. This server facilitates seamless communication between services, making it dynamic and adaptive to the ever-changing environment.

## API Gateway
To ensure consistent and efficient communication across services, I've integrated an API Gateway using Spring Cloud Gateway. This gateway ensures that all services can be accessed on the same port, providing a unified experience. The application runs on the default localhost and port 8080.

## Security
I've employed Keycloak, an Identity and Access Management(IAM) solution, to safeguard the system. This setup requires authentication tokens for all API calls, enhancing the overall security posture of the application.  

## Application Flow
The application's flow can be described as follows:

**API Gateway & Keycloak**: The initial layer of the application includes the API Gateway, responsible for routing requests, and Keycloak, ensuring secure authentication.  

**Product Service**: This service interacts with a MongoDB database to manage product-related data and operations.  

**Order Service**: Responsible for order placement and management. It communicates with the Inventory Service to verify product availability.  

## Getting Started
To run this project successfully, follow these steps:

Ensure you have MySQL database installed.
In the order-service module, update the username and password according to your database credentials.
Create two databases named order-service and inventory-service.
Please note that this application is still in the development phase, and I plan to enhance its core functionalities further.  

Thank you for considering my Shopping Application project. If you have any questions or would like to explore more about this project, please don't hesitate to reach out.