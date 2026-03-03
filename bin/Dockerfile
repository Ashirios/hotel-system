# Этап сборки
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Копируем исходники
COPY bin/pom.xml .
COPY bin/src ./src

# Собираем приложение
RUN mvn clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копируем JAR из этапа сборки
COPY --from=build /app/target/*.jar app.jar

# Копируем конфигурацию
COPY application.properties /app/application.properties

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
