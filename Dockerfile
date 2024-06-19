#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
#COPY target/lab01-0.0.1-SNAPSHOT.jar /app/lab01.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "/app/lab01.jar"]
FROM openjdk:17-jdk-oracle
COPY target/*.jar lab01.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lab01.jar"]

