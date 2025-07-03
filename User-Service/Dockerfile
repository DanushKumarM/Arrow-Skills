# 🧱 Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies (cache-efficient)
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copy the rest of the source
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# 🚀 Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim AS runner

WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/target/User-Service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
