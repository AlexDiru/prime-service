FROM openjdk:17
MAINTAINER Alex Spedding
ADD target/prime-service-0.0.1-SNAPSHOT.jar prime-service.jar
ENTRYPOINT [ "java", "-jar", "prime-service.jar" ]