# Use the official OpenJDK image
FROM maven:3-eclipse-temurin-17 AS build

# Set the working directory
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
# Copy the built jar from target
COPY --from=build /target/*.jar demo.jar

# Expose port (Render uses 8080 by default)
EXPOSE 8080

# Set the environment variable for MongoDB
ENV MONGODB_URI="mongodb://localhost:27017/your_db"

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "demo.jar"]
