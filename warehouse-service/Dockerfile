# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /src
COPY ../pom.xml /src/pom.xml
COPY ../common-lib /src/common-lib
COPY ./ /src/warehouse-service
RUN apk add --no-cache maven
RUN mvn -q -f /src/pom.xml -DskipTests -pl warehouse-service -am package

# Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /src/warehouse-service/target/*.jar /app/app.jar
EXPOSE 8085
ENTRYPOINT ["java","-XX:+UseZGC","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]
