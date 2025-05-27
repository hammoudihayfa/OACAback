FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17
WORKDIR /app
EXPOSE 8089
COPY --from=build /app/target/email-service-1.0.jar email-service.jar
ENTRYPOINT ["java", "-jar", "email-service.jar"]
