FROM openjdk:19
LABEL authors="Seth Pal"
EXPOSE 8761
ADD target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["java", "-jar","/eureka-server.jar"]