FROM openjdk:21

# Use an official OpenJDK runtime as a parent image

# Set the maintainer label
LABEL maintainer="PPS"

# Set the working directory inside the container
WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Build the project to create the JAR file
RUN ./mvnw package

# Copy the generated JAR file to the container
COPY target/AccountsMicroservice-0.0.1-SNAPSHOT.jar accounts-microservice-0.0.1-SNAPSHOT.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/app/accounts-microservice-0.0.1-SNAPSHOT.jar"]