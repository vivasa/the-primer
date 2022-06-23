# The Primer

## Overview

This project is developed to help beginners with a starting point for coding their exercises

---

## Prerequisites
- JDK 11 or higher
- Latest version of Docker and docker-compose 

## Steps to install
### Without docker, but JDK 11 is installed
- Checkout the project to your local machine using `Git`
- To build the project, run: `./gradlew build`
- To launch the service, run: `./gradlew run`
- To execute a specific gem as a standalone program, run: `./gradlew -PmainClass=<class fqn> run `