# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /usr/src/newsfeedservice

RUN apt-get update && apt-get install -y iputils-ping telnet

COPY newsfeedservice/build/libs/newsfeedservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8083

CMD ["java", "-jar", "newsfeedservice-0.0.1-SNAPSHOT.jar"]

