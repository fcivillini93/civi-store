FROM openjdk:17-alpine

# Dichiarazione delle variabili
ARG APP_NAME=""
ARG APP_VERSION=""

ENV SPRING_PROFILES_ACTIVE=docker
ENV MAX_HEAP_MEMORY="-Xmx128m"
ENV MIN_HEAP_MEMORY="-Xms64m"
ENV APP_NAME=${APP_NAME}
ENV APP_VERSION=${APP_VERSION}
ENV JAR_NAME=${APP_NAME}-${APP_VERSION}.jar
ENV APPLICATION_PORT=9000
ENV APPLICATION_PATH="/pizza-manager"
ENV APPLICATION_DEBUG_PORT=5005
ENV LOG_LOCATION="/app/log"
ENV RESOURCE_LOCATION="/app/resources"
ENV CONFIG_LOCATION="${RESOURCE_LOCATION}/config"

LABEL maintainer="fcivillini" \
      name=${APP_NAME} \
      version=${APP_VERSION}


RUN echo "Europe/Rome" > /etc/timezone

WORKDIR /app

RUN mkdir /certs

RUN mkdir ${LOG_LOCATION}
RUN mkdir -p ${CONFIG_LOCATION}

COPY ./target/${JAR_NAME} ./app.jar
COPY ./target/classes/* ${CONFIG_LOCATION}/
RUN rm -rf ./target/classes/it
COPY ./src/docker/entrypoint.sh /app/entrypoint.sh

RUN apk add --no-cache curl
RUN chmod +x /app/entrypoint.sh

EXPOSE ${APPLICATION_PORT}

ENTRYPOINT ["/app/entrypoint.sh"]

CMD ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC ${MAX_HEAP_MEMORY} ${MIN_HEAP_MEMORY} -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${APPLICATION_DEBUG_PORT} -jar -Dcom.sun.net.ssl.checkRevocation=false -Dspring.config.location=${CONFIG_LOCATION}/  -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} ./app.jar"]