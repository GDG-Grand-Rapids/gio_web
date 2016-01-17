[![Build Status](https://travis-ci.org/Google-IO-Extended-Grand-Rapids/conference_web.svg?branch=develop)](https://travis-ci.org/Google-IO-Extended-Grand-Rapids/conference_web)
[![Coverage Status](https://coveralls.io/repos/Google-IO-Extended-Grand-Rapids/conference_web/badge.svg?branch=develop)](https://coveralls.io/r/Google-IO-Extended-Grand-Rapids/conference_web?branch=develop)

# Overview
This project has undergone some massive changes.  What follows below are the 3 quick and easy steps to get up and running.  This does require that you have the following installed and it is working properly

* Docker

## Get the Google IO Database Image and run it within a container

```
docker run --name gio-db -p 5432:5432 -d gdggr/gio-db:0.0.3
```

## Get the Google IO Rest Layer adn run it connected to the database

```
docker run --name gio-rest -it --link gio-db:gio-db -p 8080:8080 -d gdggr/gio-rest:1.0.0
```

## The API can be found at this following location

```
http://192.168.99.100:8080/api/conference
```

# Developing on the REST Application
In order to develop and add features to this application, it is recommended that you run the gio-db container and run the rest application on the host machine (not in a Docker Container).  The way to run the rest application on your local machine is by using the following command:

```
mvn -PlocalDB clean package && java -DconfAdminPassword=nimda -jar target/*.jar
```

This command will compile the application using the _localDB_ profile.  The _localDB_ profile will alter the configuration of the Spring Boot application such that it connects to the docker container database.  The next command, following the &&, is to actually run the application, with the fictious admin password of _nimda_.


# Legacy Documentation below.  Please do not use this.  Needs to be updated...seriously

# Development Environment
* [VirtualBox v4.3.20+](https://www.virtualbox.org/wiki/Downloads)
* [Vagrant v1.7.2+](https://www.vagrantup.com/downloads.html)
* Git

# Commands to run the application locally
This will stand up a vagrant docker host and build/start the 2 docker containers needed to run the app.

```
$ git clone git@github.com:Google-IO-Extended-Grand-Rapids/conference_web.git
$ cd conference_web
$ vagrant up --no-parallel
```

# Connecting to the database from host machine
In order to allow this, you must destroy the Vagrant container so the appropriate ports are forwarded
- Host: localhost
- Port: 5432
- username: postgres

# Vagrant Startup with Database Only configured
If you do not want to run the web docker container, and instead would prefer to only startup the database container, then please follow the instructions below
After making these modifications, when you perform the vagrant commands (up, halt), it will only be for the db
- Copy the vagrant_config.yml.template to vagrant_config.yml
- adjust the "start-web" to be false
- Run the application with the following and it will connect to postgres using the local information
-- mvn clean install -PlocalDB && java -DconfAdminPassword=nimda -jar target/*.jar

# Database commands
Here is a list of database commands that can be ran as maven
## Rollback

```
$ mvn liquibase:rollback -Dliquibase.rollbackTag=version_1.1
```
Please be sure to always add to the end of the liquibase scripts.

# Command to do a maven build of the webapp after vagrant/docker is setup
By default, install is being used by our DockerFile during build.  Right now it's rigged to point at a specific IP address in the vagrant machine, which isn't ideal.  But hey, it works for now.

To run stuff locally and hit the postgres container in vagrant, use the 'travis' profile that has a config setup to hit localhost.

```
$ mvn clean install -Ptravis
```


# Use this command in order to populate the database appropriately
```
mvn -PlocalDB clean package && java -DconfAdminPassword=calus_is_cool -jar target/conference-web-0.1.0.jar
```


# Available API
Here is a list of the available apis in the system

- /api/conference - will retrieve all conferences
- /api/conference/{id} - will retrieve conference by id
- /api/conference/{id}/conferenceSessions - will retrieve the sessions by conference id
- /api/conferenceSession - will retrieve all conference sessions
- /api/conferenceSession/{id} - will retrieve conference session by id
- /api/presenter - will retrieve all presenters
- /api/presenter/{id} - will retrieve presenter by id

