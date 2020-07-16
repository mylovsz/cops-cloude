FROM hub.c.163.com/library/java:8-jre
ADD target/*.jar /cops-scada-server.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+8","-jar","/cops-scada-server.jar"]