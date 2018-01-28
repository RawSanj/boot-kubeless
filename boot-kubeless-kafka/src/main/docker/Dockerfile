FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD boot-kubeless-kafka-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]