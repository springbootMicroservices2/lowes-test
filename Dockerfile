FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true

FROM openjdk:13-jdk-alpine
COPY --from=build /usr/src/app/target/lowes-urlShortner 0.0.1-SNAPSHOT /usr/src/app/lowes-urlShortner 0.0.1-SNAPSHOTmvn clean insta;;
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/url-shortener-api-0.0.1-SNAPSHOT.jar"]