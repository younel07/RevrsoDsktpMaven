# using the OpenJDK 17 image as the base image
FROM openjdk:17-alpine

WORKDIR /app

COPY ReversoMaven-1.0-SNAPSHOT.jar /app/ReversoMaven-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/ReversoMaven-1.0-SNAPSHOT.jar"]