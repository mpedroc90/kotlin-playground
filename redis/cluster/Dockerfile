FROM openjdk:17-jdk
VOLUME /tmp
COPY build/libs/cluster-1.0-SNAPSHOT.jar app.jar
CMD ["sh","-c","java -jar app.jar"]