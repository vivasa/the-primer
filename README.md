# The Primer

## Overview

This project is developed to help beginners with a starting point for coding their exercises

---

## Prerequisites
- To make best use of this project, a working setup of Docker (and docker-compose) is essential
- JDK 11 or higher (not mandatory if docker is present)

## Steps to install
### With Docker and docker compose

`docker-compose up -d`

### Without docker, but JDK 11 is installed
- Checkout the project to your local machine using `Git`
- To build the project, run: `./gradlew build`
- To launch the service, run: `./gradlew run`
- To execute a specific gem as a standalone program, run: `./gradlew -PmainClass=<class fqn> run `