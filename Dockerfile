# FROM maven:3.8.8-eclipse-temurin-17
FROM ubuntu

RUN apt update && apt install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/vp-1.0.jar" ]