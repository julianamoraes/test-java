FROM openjdk:8-jre-alpine

# add directly the war
ADD *.war /app.war

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8080
CMD java -jar /app.war
