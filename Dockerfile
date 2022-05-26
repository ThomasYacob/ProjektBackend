
FROM openjdk:12
FROM maven

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
RUN mvn install -DskipTests
EXPOSE 8080
ADD ./target/backend-0.0.1-SNAPSHOT.jar /developments/
ENTRYPOINT ["java","-jar","/developments/backend-0.0.1-SNAPSHOT.jar"]