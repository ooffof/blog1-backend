FROM openjdk:8

WORKDIR /home/czl/backend

ADD . ./

CMD java -jar website.jar --spring.profiles.active=prod




