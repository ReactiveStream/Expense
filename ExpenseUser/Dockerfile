# Use an official OpenJDK 8 runtime as a parent image
FROM openjdk:8-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/ExpenseUser-0.0.1-SNAPSHOT.jar /app/ExpenseUser-0.0.1-SNAPSHOT.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "ExpenseUser-0.0.1-SNAPSHOT.jar"]
