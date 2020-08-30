## Company API with Postgres + Docker Support
### Project Description
This project contains the source code to run a standalone Spring Boot web server that hosts a REST API and is integrated with a Postgres relational DB.
The service depends on a pre-configured database being both present and available to connect to. 

### Docker Requirements
Firstly, in order to make use of the project's container support functionality, you must have a valid version of docker and docker-compose available and part of your relevant paths.

To confirm this, open up a terminal/cmd prompt and enter:
* docker --version
* docker-compose --version

Data being returned on both commands means you can proceed, if either is unavailable please follow the relevant documentation for installs:
* https://docs.docker.com/get-docker/
* https://docs.docker.com/compose/install/

### Project Set-Up
* Now that container support is up and running, git clone down the project to your local workspace.
* Once project has resolved and your IDE has determined it to be a Maven project, run the command "mvn clean install" at the project parent level.
* This will compile the Java code for the server and build an executable jar (see "company-api.jar" in api-service's target/ directory).
* You must now add the appropriate directory path to the volume mount configuration in the Postgres-service docker-compose.yml:
* Change - $DB_DATA_MOUNT variable to your designated directory - it must be empty and have full write permissions.

### Project Runtime
You can opt to run the project locally via IDE or command line or within a docker-compose service network.

#### Locally
You only need to ensure that a Postgres DB is available, so either ensure your own local DB server is running or, once you've navigated to the project directory in shell/terminal, enter:
* docker-compose run postgres-service

This will run a standalone container of only the database.

To then run the server from IDE, run the main method from org.toandeaf.company.api.Application.

#### Docker-Compose Network
Running via docker compose will not only pull and run a postgres DB, but will also bundle your server jar (generated via mvn install) and run it as its own container.

To run both server and database, navigate to the parent directory and enter:
* docker-compose run

**Note - If you run both a local instance and compose instance, both servers will attempt to listen on localhost:8080, causing an error, amend properties files accordingly**

#### Endpoints and Testing Connectivity
To test whether the services are connected and functional, navigate to:
* http://localhost:8080/api/swagger-ui/#

#### Making Changes to Server
Making and "pushing" changes to server container builds will require:
* mvn clean install
* docker-compose build
* docker-compose up

**Note - docker-compose configs will only rebuild the images in use if it is explicitly told to.**
**Also keep in mind that any compiled changes will be bundled as part of the jar during the package step**








