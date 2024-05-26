FROM maven:3.8.5-openjdk-17 as builder
LABEL authors="mateo"
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:go-offline
COPY src ./src
RUN mvn -e -B clean package -D skiptests

FROM openjdk:23-ea-17-jdk-slim
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]