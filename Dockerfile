# Author: xiaotian li
# build the app docker container
FROM maven:3.8.2-jdk-11
VOLUME /tmp
WORKDIR /bms-warehouse-keeper-api
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
