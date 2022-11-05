FROM maven:3-amazoncorretto-17 AS builder

COPY . ./energy
WORKDIR ./energy
RUN ["mvn", "clean", "package", "-Dmaven.test.skip=true"]

FROM amazoncorretto:17.0.5
EXPOSE 8090
COPY --from=builder ./energy/target/energy-0.0.1-SNAPSHOT.jar ./energy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "./energy-0.0.1-SNAPSHOT.jar"]

# docker build -t image_name .