FROM maven:3.2-jdk-7
MAINTAINER danmikita@gmail.com
EXPOSE 8080
COPY ./target/conference-web-0.1.0.jar /opt
WORKDIR /opt
CMD java -DconfAdminPassword=carlus_is_cool -jar conference-web-0.1.0.jar
