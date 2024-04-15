# Introduction
Build a SpringBoot application/service that provides a REST endpoint for querying such that:

Accept input parameters: application date, product identifier, brand identifier.

Return output data: product identifier, brand identifier, applicable rate, application dates, and final price to apply.

Endpoint:
GET http://localhost:8081/v1/prices?date={date}&product-id={productId}&brand-id={brandId}

# Decisions
The exercise has been tackled by implementing a Hexagonal architecture, adhering to the SOLID principles. 
This architecture aims to isolate the domain layer from infrastructure and external dependencies, minimizing 
the potential impact of future changes, such as a database switch. Consequently, even Lombok hasn't been utilized 
in the domain layer.


To improve the database query performance, a composite index has been created on 
"start_date, end_date, product_id, brand_id, priority". Another potential improvement, not implemented, 
could be to add a distributed cache like Redis.

Unit tests have been performed (with 100% coverage of classes, 87% of methods, and 81% of tested lines).

Integration tests (PriceIntegrationTest) have been developed to validate the following requests:

- Test 1: request at 10:00 on the 14th day for product 35455 for brand 1 (ZARA)
- Test 2: request at 16:00 on the 14th day for product 35455 for brand 1 (ZARA)
- Test 3: request at 21:00 on the 14th day for product 35455 for brand 1 (ZARA)
- Test 4: request at 10:00 on the 15th day for product 35455 for brand 1 (ZARA)
- Test 5: request at 21:00 on the 16th day for product 35455 for brand 1 (ZARA)

# Technologies and libraries
- Spring Boot 3.2.2
- Java 17
- H2 2.2.224
- Open API 2.2.0
- Lombok 1.18.30
- Junit 5
- Mockito 5


- H2 has been used as an in-memory database which can be accessed at http://localhost:8081/h2-console
- The REST service is available at http://localhost:8081/swagger-ui/index.html