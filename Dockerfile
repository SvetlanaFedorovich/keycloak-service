FROM openjdk:17-jdk-alpine3.14
COPY ./build/libs/keycloak-service-1.0.1.jar keycloak-service.jar
ENTRYPOINT ["java", "-jar", "keycloak-service.jar"]
