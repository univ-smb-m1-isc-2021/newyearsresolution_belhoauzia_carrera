FROM openjdk:11-jre-slim

COPY ./target/resolution-SNAPSHOT.jar .

EXPOSE 8080

CMD ["sh","-c","java -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=70  -XshowSettings $JAVA_OPTS -jar resolution-SNAPSHOT.jar"]
