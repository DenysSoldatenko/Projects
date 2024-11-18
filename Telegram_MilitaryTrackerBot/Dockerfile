FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY build.gradle gradle.properties settings.gradle ./
COPY src/main/ ./src/main/
RUN gradle build --no-daemon
RUN gradle clean build -x test --no-daemon

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar application.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]