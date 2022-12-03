# Building and running

(Application runs on port 8080)

Run with Maven: `./mvnw spring-boot:run`

Package with Maven: `./mvnw package`

Build with Docker: `docker build -t prime-service:latest .`

Run with Docker: `docker run -p 8080:8080 prime-service`

# Run Tests

`./mvnw test`

# Using the application

Read JSON: `curl -v -H "Accept: application/json" http://localhost:8080/primes/1000`

Read XML: `curl -v -H "Accept: application/xml" http://localhost:8080/primes/1000`