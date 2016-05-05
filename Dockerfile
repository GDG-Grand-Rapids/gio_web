FROM maven:3.2-jdk-7
MAINTAINER danmikita@gmail.com
EXPOSE 8080
COPY . /opt/conference_web
WORKDIR /opt/conference_web
CMD java -DconfAdminPassword=carlus_is_cool -jar /opt/conference_web/target/*.jar