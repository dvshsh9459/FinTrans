
FROM adoptopenjdk:17-jdk-hotspot
COPY target/booking-api.jar booking-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/booking-api.jar"]
