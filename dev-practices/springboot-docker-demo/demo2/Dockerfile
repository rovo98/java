FROM openjdk:8-jdk-alpine

MAINTAINER rovo98 <rovo984sff@gmail.com>

VOLUME /tmp

ADD target/demo2-0.0.1-SNAPSHOT.jar app2.jar

#ENV JAVA_OPTS=""

#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app2.jar" ]
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app2.jar" ]
