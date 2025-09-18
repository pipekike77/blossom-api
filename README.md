# Blossom eCommerce API

Blossom API is a RESTful API for an e-commerce platform developed with Spring Boot. It supports basic functionalities such as authentication, product management, orders, payments, and users.

## Description

This repository contains the backend layer of an e-commerce system. Its features include:

- Product management

- User authentication and registration with JWT

- Order and payment management

- User history and statistics

- Security with Spring Security

- Automatic documentation with OpenAPI / Swagger UI

- Embedded H2 database for development and testing

## Technologies
```bash
| Technology | Version |
| --- | --- |
| Java | 17  |
| --- | --- |
| Spring Boot | 3.1.8 (compatible) |
| --- | --- |
| Database | H2 (embedded) |
| --- | --- |
| Security | Spring Security + JWT |
| --- | --- |
| API Documentation | springdoc-openapi (Swagger UI) |
| --- | --- |
| Build | Maven |
| --- | --- |
```
## How to run the project

### **Clone the repository**
```bash
git clone <https://github.com/pipekike77/blossom-api.git>

cd blossom-api
```
### **Option 1: Run directly with Maven**
```bash
./mvnw spring-boot:run
```
### **Option 2: Use scripts to facilitate execution**
### **Linux / macOS**
```bash
chmod +x run.sh

./run.sh
```
### **Windows**

Double click on run.bat or run it from CMD:
```bash
run.bat
```
### **Remember to have Java 17 or higher installed.**

### Project structure

The application will run at:

http://localhost:8080

Project Structure
```bash
src
├── main
│   ├── java/com.ecommerce.blossom.api
│   │   ├── config              # General configurations
│   │   ├── controllers         # REST controllers
│   │   ├── dtos                # Data Transfer Objects
│   │   ├── entities            # JPA entities
│   │   ├── enums               # Enums for fixed attributes
│   │   ├── events              # Order created event
│   │   ├── exceptions          # Handlers and exceptions
│   │   ├── filters             # Custom query filters for Product
│   │   ├── listeners           # Asynchronous order payment event
│   │   ├── mappers             # Entity to DTO mapping
│   │   ├── repositories        # JPA interfaces
│   │   ├── security            # Security and JWT configuration
│   │   └── services            # Business logic
│   └── resources
│       ├── application.properties
```

- /src/main/java/... – Source code (controllers, services, repositories)

- /src/main/resources/application.properties – Project configuration

- /run.sh and /run.bat – Scripts to easily run the application

- /Blossom eCommerce API.postman_collection.json – Postman collection to test the API

## API Documentation (Swagger UI)

When starting the application, you can access the interactive documentation at:

http://localhost:8080/swagger-ui.html

There you will find all available endpoints with their descriptions, parameters, and models.

## Embedded H2 Database

For development and testing, the embedded H2 database is used. You can access the H2 console at:

<http://localhost:8080/h2-console>
```bash
- JDBC URL: jdbc:h2:mem:testdb  
- User: sa  
- Password: (empty)
```  
## Postman Collection
Include a Postman collection to facilitate manual testing of the API. You can import it into Postman:

Blossom eCommerce API.postman_collection.json

For testing user ADMIN already created 
```bash
email: admin@blossom.com
password: admin123
```

## Configuration

You can modify the application properties at:

src/main/resources/application.properties

Here you can configure:

- Server port

- Database configuration

- Security properties

- Other parameters

## Database Diagram

The ER (Entity-Relationship) diagram of the database so you can understand the structure and relationships.

<img width="1536" height="1024" alt="Blossom DB ER diagram" src="https://github.com/user-attachments/assets/25f3e9c6-61a1-4f70-8aec-2996ecd49f21" />

## Authentication

The API uses JWT to protect endpoints.

Public endpoints:

POST /auth/register → Create new user

POST /auth/login → Get JWT token

Protected endpoints:

All others (/products, /orders, /users, etc.) require the token in the header:
```bash
Authorization: Bearer <your_jwt_token>
```
Featured endpoints
```bash
Method	Endpoint	              Description                        Access
POST	  /auth/register	        User registration                  Public
POST	  /auth/login	            Login and obtain JWT               Public
GET	    /products	              Get all products                   ADMIN, CUSTOMER
GET	    /products/{id}	        Get product by id                  ADMIN, CUSTOMER
GET	    /products/best-selling  Get best selling products          ADMIN, CUSTOMER
DELETE  /products/{id}	        Delete product by id               ADMIN
PUT     /products/{id}	        Update product by id               ADMIN
POST    /products	              Create product                     ADMIN
POST	  /orders	                Create new order                   ADMIN, CUSTOMER
POST	  /payments/process	      Process manual payment             ADMIN, CUSTOMER
GET	    /users/profile	        Get user profile by email          ADMIN, CUSTOMER
GET	    /users/top	            Get list o users with most orders  ADMIN, CUSTOMER
GET	    /users/order-history	  User order history                 ADMIN, CUSTOMER
```
## Upcoming improvements

Add support for external databases (MySQL / PostgreSQL)

Add more granular roles and permissions

Differentiate between DTO responses and requests

CRUD for user management

More personal customer information and login logs

Finish testing

Implement Logger for console error monitoring

Deployment with Docker or cloud platforms

##Contributions

Contributions are welcome! You can open issues or pull requests for improvements or fixes.

# **Contact**
If you have questions or comments, contact me at:

Email: andresfelipe77@gmailcom
