FROM eclipse-temurin:25-jdk
LABEL authors="kaiqu"

WORKDIR /app
COPY build/libs/*.jar app.jar
COPY .env .env


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]