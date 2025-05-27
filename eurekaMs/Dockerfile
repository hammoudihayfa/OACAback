FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8761
COPY target/eurekaMs-1.0.jar eurekaMs.jar
ENTRYPOINT ["java", "-jar", "eurekaMs.jar"]
