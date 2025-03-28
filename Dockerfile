FROM ubuntu:latest

# Install dependencies
RUN apt update && apt install -y openjdk-17-jdk

# Copy JAR file
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Set environment variable
ENV DEVOPS=<username>

# Expose port
EXPOSE 8080

# Run application
CMD ["java", "-jar", "app.jar"]