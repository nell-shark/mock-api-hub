# Build stage
FROM gradle:8.3.0-jdk20-alpine AS BUILD_STAGE

WORKDIR /backend

COPY build.gradle settings.gradle ./

COPY gradle ./gradle

RUN gradle --no-daemon clean dependencies

COPY . ./

RUN gradle --no-daemon clean bootJar

# Run stage
FROM openjdk:21-oracle

WORKDIR /backend

COPY --from=BUILD_STAGE /backend/build/libs/*.jar ./backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]
