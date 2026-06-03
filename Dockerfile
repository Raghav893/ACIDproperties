FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/acid.jar acid.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "acid.jar"]