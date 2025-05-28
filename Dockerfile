FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/tsx-1.1.4.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]