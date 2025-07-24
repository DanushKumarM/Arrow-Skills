# ---- Stage 1: Build the application ----
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the application ----
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the packaged jar from builder stage
COPY --from=builder /app/target/User-Service-0.0.1-SNAPSHOT.jar app.jar

# Expose application port (change if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
