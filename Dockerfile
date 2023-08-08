FROM openjdk:17-alpine
ARG OPTIONS
WORKDIR /
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT env && java -XX:MaxRAMPercentage=100 -XX:MinRAMPercentage=100 -XshowSettings:vm -version && java -XX:MaxRAMPercentage=100 -XX:MinRAMPercentage=100 ${OPTIONS} -jar /app.jar
