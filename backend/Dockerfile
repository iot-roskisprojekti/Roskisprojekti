FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

COPY pom.xml .
COPY adapter/pom.xml adapter/
COPY application/pom.xml application/
COPY domain/pom.xml domain/
COPY bootstrap/pom.xml bootstrap/
# This command now works because all POMs are present
RUN mvn dependency:go-offline -B

COPY . .

RUN mvn clean package -DskipTests -pl bootstrap -am

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/bootstrap/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]