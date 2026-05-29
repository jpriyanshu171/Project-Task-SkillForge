FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/skillforge-backend-1.0.0.jar ./skillforge-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/skillforge-backend.jar"]
