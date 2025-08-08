<<<<<<< HEAD
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/quarkus-run.jar /app/app.jar
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/
RUN chmod -R 775 /app
ENV JAVA_OPTS="-Djava.util.logging.manager=org.jboss.logmanager.LogManager"
EXPOSE 9090
=======
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/quarkus-run.jar /app/app.jar
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/
RUN chmod -R 775 /app
ENV JAVA_OPTS="-Djava.util.logging.manager=org.jboss.logmanager.LogManager"
EXPOSE 9090
>>>>>>> 060e373aa7ea42d5bbb64ecacd8113f262627fb7
CMD ["java", "-jar", "/app/app.jar"]