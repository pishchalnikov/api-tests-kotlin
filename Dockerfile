FROM openjdk:14-jdk-alpine

WORKDIR .

COPY . .

ENTRYPOINT exec ./gradlew clean build
