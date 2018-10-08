FROM registry.opensource.zalan.do/stups/openjdk:latest

EXPOSE 8080

COPY target/weather-0.0.1-SNAPSHOT.jar /weather.jar

CMD java -jar /weather.jar
