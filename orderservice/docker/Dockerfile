# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/orderservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY orderservice/build/libs/orderservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8085

CMD ["java", "-jar", "orderservice-0.0.1-SNAPSHOT.jar"]

