1. Use a minimal, correct base image

Options:
- eclipse-temurin:17-jre
- gcr.io/distroless/java17

2. Use multi-stage builds

``` Dockerfile
# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

3. Layer optimization
Spring boot jars can be layered

``` java
java -Djarmode=layertools -jar app.jar extract
```

4. Dont run as root

``` Dockerfile
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring
```