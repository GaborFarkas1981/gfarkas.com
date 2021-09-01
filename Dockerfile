FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ADD ./target/Media-Markt-0.0.1-SNAPSHOT.jar mediamarkt.jar
ENTRYPOINT ["java", "-jar", "mediamarkt.jar"]
