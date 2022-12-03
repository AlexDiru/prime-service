# Run Application

- Application runs on port 8080

`./mvnw spring-boot:run`

### Read JSON

`curl -v -H "Accept: application/json" http://localhost:8080/primes/1000`

### Read XML

`curl -v -H "Accept: application/xml" http://localhost:8080/primes/1000`

# Run Tests

`./mvnw test`