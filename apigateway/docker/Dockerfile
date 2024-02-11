# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/apigateway

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY apigateway/build/libs/apigateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "apigateway-0.0.1-SNAPSHOT.jar"]