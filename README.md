# Microservice description

The idea of this microservice is to be able to store and maintain orders from different clients.
Exposing Rest endpoints to handle all the CRUD operations on the orders.

# Steps to run the application

## using Docker Compose
 
 **Pre Requisite:** 
  Install Docker Compose or have Docker Desktop running.


1. Navigate to root folder of the project `/Orders`
2. Run the following command `./gradlew clean build`
3. Navigate to src/main/docker directory
4. Copy the `order-1.0.jar` jar file from /build/libs/ to src/main/docker
5. Run the following command `docker-compose build` this is to build the image for the Jar generated.
6. Run the following command `docker-compose up` to run the image.
7. In the console you should see the application to be running on port 8080.
8. You can access the swagger using `http://localhost:8080/order/swagger-ui/index.html#/`


## Using gradle wrapper

1. Navigate to root folder of the project `/order`
2. run the following command `./gradlew clean bootRun`
3. In the console you should see the application to be running on port 8080.
4. you can access the swagger using `http://localhost:8080/order/swagger-ui/index.html#/`

