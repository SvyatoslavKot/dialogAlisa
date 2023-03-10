FROM openjdk:14-jdk-alpine
MAINTAINER Kotov Svyatoslav
ADD /target/dialogAlisa-0.0.1-SNAPSHOT.jar dialog_Alice.jar
ENV SERVER_PORT=${PORT}
ENV SPRING_DATASOURCE_URL=${DATA_SOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${DATA_SOURCE_NAME}
ENV SPRING_DATASOURCE_PASSWORD=${DATA_SOURCE_PASSWORD}
ENTRYPOINT java -jar /dialog_Alice.jar
EXPOSE 8070