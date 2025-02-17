# Use the official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from target
COPY --from=build /target/*.jar app.jar

# Expose port (Render uses 8080 by default)
EXPOSE 8080

# Set the environment variable for MongoDB
ENV MONGODB_URI="mongodb://localhost:27017/your_db"

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
