FROM gradle:8.7.0-jdk21 AS builder

WORKDIR /app
ENV PG_HOST=172.21.160.1

COPY ./src ./src
COPY ./build.gradle ./

RUN gradle build

RUN mv $(find ./build/libs -type f -name "*.jar" ! -name "*plain*.jar" -print -quit) ./app.jar

FROM openjdk:21

WORKDIR /app

COPY --from=builder /app/app.jar ./app.jar

ENV PG_HOST=172.21.160.1

ENTRYPOINT ["java", "-jar", "app.jar"]