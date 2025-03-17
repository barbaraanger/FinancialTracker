FROM gradle:8.12.1-jdk23 AS build

WORKDIR /app

COPY --chown=gradle:gradle build.gradle settings.gradle /app/
COPY --chown=gradle:gradle gradle /app/gradle


COPY --chown=gradle:gradle . .

RUN gradle clean build --no-daemon || return 0

FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["spring-boot:run", "-Dspring-boot.run.profiles=dev", "-Dspring.devtools.restart.enabled=true", "-Dspring.devtools.livereload.enabled=true"]