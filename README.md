# Shop Microservice

This project is a simple Java 17 microservice for managing items in a shop. It provides a RESTful API to perform CRUD operations on items. The service uses Spring Boot and is configured with basic security to protect its endpoints.

## Project Structure

- **UI Layer**: Contains REST controllers and handles incoming requests.
- **Application Layer**: Manages business logic and service interactions.
- **Domain Layer**: Contains core domain objects and entities.
- **Infrastructure Layer**: Provides the implementation of technical capabilities such as database access, messaging, and external service integrations. This layer contains repositories, configuration classes, and any utilities required to support the application.

## Getting Started

### Prerequisites

- Java 17
- Maven
- PostgreSQL for database
- Swagger/OpenApi

### Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd shop
Build the application:
-mvn clean install

Run the application:
-mvn spring-boot:run

Access the application:
-The application runs on http://localhost:8081.

Security Configuration
-This application uses basic authentication for security. The default credentials are:
*Username: user
*Password: The password is generated and displayed in the logs when the application starts (e.g., abe9ee67-ea18-4cdd-b9b9-12d4dfb04a35). Use this password to authenticate during each request.

Run the following command in your terminal to start PostgreSQL in detached mode:

2 **Start PostgreSQL**:
```bash
   docker-compose up -d
  ```
docker-compose is the command to manage multi-container Docker applications.
up starts the services defined in your docker-compose.yml file.
-d runs the containers in detached mode (in the background).

3 **Verify PostgreSQL is Running**:
   To check if PostgreSQL is running, use:
```bash
docker-compose ps
```


API Endpoints:

Method	Endpoint	    Description
POST	/item/add	    Add a single item to the shop.
POST	/item/list/add	Add multiple items to the shop.
GET	    /item/list	    Retrieve a list of items from the shop.
GET	    /item/get	    Retrieve a specific item by ID.
POST	/item/delete	Delete an item from the shop by ID.
POST	/item/update	Update an existing item in the shop.

Endpoint Descriptions:

-Add Item
Endpoint: /item/add
Method: POST
Request Body:
json
{
"name": "Item Name",
"price": 19.99
}

-Add Item List
Endpoint: /item/list/add
Method: POST
Request Body:
json
{
"items": [
{
"name": "Item Name 1",
"price": 29.99
},
{
"name": "Item Name 2",
"price": 39.99
}
]
}

-Get Item List
Endpoint: /item/list
Method: GET
Query Parameters:
pageSize: Number of items per page (default: 10)
pageNumber: Current page number (default: 0)

-Get Item
Endpoint: /item/get
Method: GET
Request Body:
json
{
"id": UUID
}

-Delete Item
Endpoint: /item/delete
Method: POST
Request Body:
json
{
"id": UUID
}

-Update Item
Endpoint: /item/update
Method: POST
Request Body:
json
{
"id": UUID,
"name": "Updated Item Name",
"price": 24.99
}

Example Usage with Postman
Setting up Basic Auth:

In Postman, for any request to the API, set the Authorization to Basic Auth.
Use user as the username and the generated password from the logs as the password.
Making a Request:

Select the method (GET, POST) and enter the URL.
Add the required body or parameters as per the endpoint description.
Click Send to execute the request.
Logs and Password Retrieval
When the application starts, the console will display a message similar to:
   
Using generated security password: abe9ee67-ea18-4cdd-b9b9-12d4dfb04a35
Make sure to use this password for authentication in Postman.

## Using RabbitMQ for HTTP Tracing

This microservice utilizes RabbitMQ to send HTTP trace messages to a dedicated queue. Each time an HTTP request is made, a trace message containing the request method and URI is published to the RabbitMQ queue. This setup allows for asynchronous processing of trace data, enhancing the application's observability and performance.

### Key Features

- **Asynchronous Messaging**: HTTP requests are processed without waiting for trace handling, ensuring faster response times.
- **Decoupled Architecture**: The producer and consumer of trace messages are independent, promoting maintainability and scalability.
- **Monitoring and Analysis**: Trace messages can be consumed by separate services for logging, monitoring, or further analysis.

### Setup Instructions

1. **Add RabbitMQ Dependencies**: Include the necessary dependencies in your `pom.xml`.
2. **Configure Connection**: Set up RabbitMQ connection details in `application.properties`.
3. **Run RabbitMQ**: Start RabbitMQ locally using Docker or any other preferred method.

This approach leverages RabbitMQ's robust messaging capabilities, enabling efficient handling of HTTP traces for better observability in your application.
Use docker:
```bash
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Conclusion
This microservice provides a straightforward way to manage shop items with basic security in place. You can enhance it further by implementing additional features such as caching, logging, or using a persistent database.


