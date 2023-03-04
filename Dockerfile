# Author: xiaotian li

# Firstly, run command `mvn clean package` to build jar
# Then,
# docker build command: docker build -t 996workers.icu/bms-warehouse-keeper-api .
# env vars are used in run command: see .env.example file in the project root directory
# run container with:

# TODO: docker compose with SQL server...

FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY target/bms-warehouse-keeper-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
