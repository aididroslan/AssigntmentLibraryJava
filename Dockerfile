# Use an official Java runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/LibraryApplication.jar /app/LibraryApplication.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/LibraryApplication.jar"]