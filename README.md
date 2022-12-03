### Building and running

(Application runs on port 8080)

Run with Maven: `./mvnw spring-boot:run`

Package with Maven: `./mvnw package`

Build with Docker: `docker build -t prime-service:latest .`

Run with Docker: `docker run -p 8080:8080 prime-service`

### Run Tests

`./mvnw test`

### Using the application

Read JSON: `curl -v -H "Accept: application/json" http://localhost:8080/primes/1000`

Read XML: `curl -v -H "Accept: application/xml" http://localhost:8080/primes/1000`

### Api Docs

Accessible at `http://localhost:8080/swagger-ui/index.html`

### Requirements

**Compulsory**

- The project must be written in Java 8 or above. ✅
- The project must use Maven OR Gradle to build, test and run. ✅
- The project must have unit and integration tests. ✅
- The project must run, in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring
Boot etc. ✅

**Optional**

- Consider supporting varying return content type such as XML based on requested media type. ✅
- Consider ways to improve performance e.g. caching ✅, threading ✅
- Consider supporting multiple algorithms based on optional parameters ❌
- Consider adding API documentation ✅
- Consider packaging it as a runnable Docker image ✅