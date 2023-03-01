# The Primer

## Overview

This project is developed to help beginners with a starting point for coding their exercises.
This application can be run as a service and can be also launched as a script from the commandline.

### Service

To launch the service, run the following from the commandline:

```
./gradlew run
```

For Windows the command to launch the service is 
```
.\gradlew.bat run
```
---

## Installation
### Prerequisites
- To make best use of this project, a working setup of Docker (and docker-compose) is essential
- JDK 11 or higher (not mandatory if docker is present)

### Steps to install
> With Docker and docker compose

`docker-compose up -d`

### Without docker, but JDK 11 is installed
- Checkout the project to your local machine using `Git`
- To build the project, run: `./gradlew build`
- To launch the service, run: `./gradlew run`
- To execute a specific gem as a standalone program, run: `./gradlew -PmainClass=<class fqn> run `

<!-- ### Windows Powershell -->

<!-- `PS C:\> cat script.js | docker run --rm -i grafana/k6 run -` -->
---
## Developer Notes
### Building the docker image locally
`./gradlew dockerBuild -Pversion=<version number>`

### Docker image location
At present, the latest docker image of the project is accessible at `public.ecr.aws/vivasa/the-primer:latest`