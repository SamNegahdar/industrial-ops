# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /src
COPY ../pom.xml /src/pom.xml
COPY ../common-lib /src/common-lib
COPY ./ /src/inventory-service
RUN apk add --no-cache maven
RUN mvn -q -f /src/pom.xml -DskipTests -pl inventory-service -am package

# Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /src/inventory-service/target/*.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java","-XX:+UseZGC","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]
