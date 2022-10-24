FROM maven:3.8.4-eclipse-temurin-17 as build
WORKDIR /app
COPY . /app
RUN mvn clean install

FROM eclipse-temurin:17.0.2_8-jre-alpine
COPY --from=build /app/application/target/*.jar /exchange.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/exchange.jar"]