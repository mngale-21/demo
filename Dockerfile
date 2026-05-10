# Hatua ya 1: Kujenga mradi (Build stage)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Nakili pom.xml na src kwanza ili kuharakisha build
COPY pom.xml .
COPY src ./src
# Jenga mradi na utengeneze .jar file bila kurun tests
RUN mvn clean package -DskipTests

# Hatua ya 2: Kurun mradi (Run stage)
FROM openjdk:17-jdk-slim
WORKDIR /app
# Nakili jar file kutoka kwenye build stage
COPY --from=build /app/target/*.jar app.jar
# Fungua port 8080 (standard kwa Spring Boot)
EXPOSE 8080
# Amuru system iwake
ENTRYPOINT ["java", "-jar", "app.jar"]