####Stage 1
FROM openjdk:12
FROM maven
# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never


####Stage 2
# Image layer: with the application
COPY . .
RUN mvn -vs
RUN mvn install -DskipTests


####Stage 3
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/backend-0.0.1-SNAPSHOT.jar"]