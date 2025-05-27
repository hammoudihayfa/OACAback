FROM openjdk:17
EXPOSE 8096
ADD target/APIGetway-0.0.1-SNAPSHOT.jar apiGateway.jar
ENTRYPOINT ["java", "-jar", "apiGateway.jar"]
