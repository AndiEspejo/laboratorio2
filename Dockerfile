FROM eclipse-temurin:11-jre
WORKDIR /app
COPY target/faker.jar faker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "faker.jar"]
