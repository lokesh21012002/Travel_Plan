#FROM ubuntu:latest
#LABEL authors="lokesh"
#
#ENTRYPOINT ["top", "-b"]



FROM openjdk:21
EXPOSE 8080
ADD target/travel-plan.jar travel-plan.jar
ENTRYPOINT ["java","-jar","/travel-plan.jar"]