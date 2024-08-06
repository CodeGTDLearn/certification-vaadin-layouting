#STAGE 01: Buildando a APP com Maven
FROM maven:3.8.4-openjdk-17-slim AS buildinho
WORKDIR /app
COPY pom.xml ./
COPY src ./src/.
RUN mvn clean install

#STAGE 02: Run the App
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=buildinho app/target/*.jar ./layout-vaadin-aws.jar
EXPOSE 8080
CMD ["java", "-jar","layout-vaadin-aws.jar"]