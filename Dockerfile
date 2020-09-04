FROM openjdk:13-jdk-alpine
EXPOSE 5000
ARG JAR_FILE=target/search-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
