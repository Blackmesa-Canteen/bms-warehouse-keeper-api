# Introduction
Black Mesa Inventory system - An inventory system which can keep track of stock for a warehouse. Backend RESTful API project

# Guide
This guide instruct how to build & run the project.

## Environment Variables
Required env vars are listed below with example values
- SYS_PORT=8080
- JWT_KEY={{some secret}}
- JWT_ISSUER=www.996workers.icu
- JWT_VALIDITY_MS=600000
- MYSQL_DB_HOSTNAME=localhost
- MYSQL_DB_PASSWORD={{some password}}
- MYSQL_DB_PORT=3306
- MYSQL_DB_USERNAME=root
- MYSQL_DB_SCHEMA=bms_warehouse_keeper

## Docker (recommended)
- Docker compose is needed. Two containers in runtime: bms-wk-api-service and bms-wk-api-mysql-service
- Dockerfile is located in the project root dir;
- Docker network is needed for localhost MySQL access in the container;

**Guides:**

## JRE
- Java 11: Check the `.jar` file in release page.
- MySQL 8: Dumped sql file is in `/migration`, use the latest one to set up database.
- Use Maven to install dependencies.
- Executable: `src/main/java/io/bms/bmswk/BMSWKApiApplication.java`.

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web)

# API requests
