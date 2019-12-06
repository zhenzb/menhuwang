FROM hub.c.163.com/library/java:8-alpine

MAINTAINER gavin gavin_i@126.com

ADD target/*.jar qingyun.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/qingyun.jar"]