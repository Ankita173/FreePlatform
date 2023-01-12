FROM openjdk:8-jdk-alpine
COPY target/dataaccess-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["jar","-jar","/app.jar"]
