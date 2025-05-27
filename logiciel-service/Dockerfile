FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8084
COPY target/logiciel-service-1.0.jar logiciel-service.jar
ENTRYPOINT ["java", "-jar", "logiciel-service.jar"]
