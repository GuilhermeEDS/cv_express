FROM ubuntu:latest AS build
LABEL authors="guilh"

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:22-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/cv_express-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]