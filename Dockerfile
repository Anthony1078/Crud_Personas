FROM eclipse-temurin:17-jdk-alpine

COPY target/*-runner.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
