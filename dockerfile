FROM amazoncorretto:17-alpine-jdk
MAINTAINER durszlak
WORKDIR /app
COPY target/k8s-0.0.1-SNAPSHOT.jar /app/k8s-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/k8s-0.0.1-SNAPSHOT.jar"]