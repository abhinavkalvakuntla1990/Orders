# Steps to run the application 


## using Docker Compose
 
 **Pre Requisite:** 
  Install Docker Compose or have Docker Desktop running.


1. Navigate to root folder of the project `/order`
2. run the following command `./gradlew clean build`
3. Navigate to src/main/docker directory
4. copy the jar file from /build/libs/ to src/main/docker
5. run the following command `docker-compose build` this is to build the image for the Jar generated.
6. run the following command `docker-compose up` to run the image.
7. In the console you should see the application to be running on port 8080.
8. you can access the swagger using `http://localhost:8080/order/swagger-ui/index.html#/`


## Using gradle wrapper

1. Navigate to root folder of the project `/order`
2. run the following command `./gradlew clean bootRun`
3. In the console you should see the application to be running on port 8080.
4. you can access the swagger using `http://localhost:8080/order/swagger-ui/index.html#/`

