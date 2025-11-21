# Etapa de build
FROM gradle:jdk23 AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew assemble --stacktrace --info

# Etapa final (execução com Java 25)
FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
