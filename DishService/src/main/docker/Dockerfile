# Stage 1: Build the Quarkus application
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=build /app/target/quarkus-app /app/quarkus-app
EXPOSE 8080
CMD ["java", "-jar", "/app/quarkus-app/quarkus-run.jar"]