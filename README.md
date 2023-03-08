# Introduction
Black Mesa Inventory system - An inventory system which can keep track of stock for a warehouse. Backend RESTful API project

# Guide
This guide instruct how to build & run the project.

# API Documentations
Users can get API documentation in *http://SYSTEM_URL/swagger-ui.html*.
Postman Scripts in `/doc`.

## Environment Variables
Required env vars are listed below with example values.
- APP_PORT=8080
- JWT_KEY={{some secret}}
- JWT_ISSUER=www.996workers.icu
- JWT_VALIDITY_MS=600000
- MYSQL_DB_HOSTNAME=localhost **# Will not be used in docker-compose**
- MYSQL_DB_PASSWORD={{some password}}
- MYSQL_DB_PORT=3306
- MYSQL_DB_USERNAME=root
- MYSQL_DB_SCHEMA=bms_warehouse_keeper
- MYSQL_DB_DOCKER_HOST_PORT=3306 **# Used in docker to indicate exposed port to host machine.**
- APP_DOCKER_HOST_PORT=8080 **# Used in docker to indicate exposed port to host machine.**

See `/.env.example` for more information.

## Docker (recommended)
### Notes
- Docker compose plugin is required. See [Docker Docs: Install the Compose Plugin](https://docs.docker.com/compose/install/).
- `/Dockerfile` and `/docker-compose` is located in the project root dir;

### Build step
Steps to build docker-compose are shown below:
1. Create `.env` file, based on `.env.example` to hold environment vars and secretes;
2. Run docker-compose with `docker-compose up`. Services can be run on the background by `docker-compose up -d`;
3. In host machine, access api system with port defined in `APP_DOCKER_HOST_PORT`, access database with port defined in `MYSQL_DB_DOCKER_HOST_PORT`; 
4. Stop the system: `docker-compose down`;
5. Stop and remove all containers, images: `docker-compose down --rmi all`.

## JRE
- Java 11: Get the `.jar` file in release page.
- MySQL 8.
- Use Maven to install dependencies.
- Set env variables for JVM.
- Executable: `src/main/java/io/bms/bmswk/BMSWKApiApplication.java`.

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.8/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web)
