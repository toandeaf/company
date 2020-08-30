FROM openjdk:8

COPY target/contrast-api.jar /app/contrast-api.jar
COPY target/classes/container.properties /app/application.properties

ENTRYPOINT ["java", "-jar", "/app/contrast-api.jar", "--spring.config.location=/app/application.properties"]