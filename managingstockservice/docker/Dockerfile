# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/managingstockservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY managingstockservice/build/libs/managingstockservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8086

CMD ["java", "-jar", "managingstockservice-0.0.1-SNAPSHOT.jar"]

