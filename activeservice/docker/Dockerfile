# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/activeservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY activeservice/build/libs/activeservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8082

CMD ["java", "-jar", "activeservice-0.0.1-SNAPSHOT.jar"]
