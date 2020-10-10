# Features - Backend Challenge

## Project stack
The project is built with spring boot with maven and java 11.
So to run the project please make sure you have confiured maven (version 3.6 or later) and java (version 11 or later).

## Running the tests
To run the existing tests you can go open the terminal on the project directory and run: `mvn clean verify`

## Running the service
To spin up service itself you can go open the terminal on the project directory and run: `mvn spring-boot:run`

P.S.: the default service port is `8080`,
e.g.: [http://localhost:8080/features](http://localhost:8080/features).
If you need to specify another port you can use the `--server.port` property,
e.g.: `mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081`
and the previous example URL becomes [http://localhost:8081/features](http://localhost:8081/features).  