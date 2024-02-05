# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/userservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY userservice/build/libs/userservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

CMD ["java", "-jar", "userservice-0.0.1-SNAPSHOT.jar"]

