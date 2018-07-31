FROM openjdk:8-jdk-alpine
ADD target/projetuc.jar projetuc.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","projetuc.jar"]