# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/productservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY productservice/build/libs/productservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8084

CMD ["java", "-jar", "productservice-0.0.1-SNAPSHOT.jar"]
