[![Build Status](https://travis-ci.org/Google-IO-Extended-Grand-Rapids/conference_web.svg?branch=develop)](https://travis-ci.org/Google-IO-Extended-Grand-Rapids/conference_web)
[![Coverage Status](https://coveralls.io/repos/Google-IO-Extended-Grand-Rapids/conference_web/badge.svg?branch=develop)](https://coveralls.io/r/Google-IO-Extended-Grand-Rapids/conference_web?branch=develop)


# Overview
This project has undergone some massive changes.  What follows below are the 3 quick and easy steps to get up and running.  This does require that you have the following installed and it is working properly

* Docker

## Get the Google IO Database Image and run it within a container

```
docker run --name gio-db -p 5432:5432 -d gdggr/gio-db
```

## Get the Google IO Rest Layer adn run it connected to the database

```
docker run --name gio-rest -it --link gio-db:gio-db -p 8080:8080 -d gdggr/gio-rest
```

## The API can be found at this following location

```
http://192.168.99.100:8080/api/conference
```

# Developing on the REST Application
In order to develop and add features to this application, it is recommended that you run the gio-db container and run the rest application on the host machine (not in a Docker Container).  The way to run the rest application on your local machine is by using the following command:

```
mvn clean package && java -Dspring.profiles.active=local -DconfAdminPassword=nimda -jar target/*.jar
```

This command will compile the application using the _localDB_ profile.  The _localDB_ profile will alter the configuration of the Spring Boot application such that it connects to the docker container database.  The next command, following the &&, is to actually run the application, with the fictious admin password of _nimda_.


# Available API
Here is a list of the available apis in the system

- /api/conference - will retrieve all conferences
- /api/conference/{id} - will retrieve conference by id
- /api/conference/{id}/conferenceSessions - will retrieve the sessions by conference id
- /api/conferenceSession - will retrieve all conference sessions
- /api/conferenceSession/{id} - will retrieve conference session by id
- /api/presenter - will retrieve all presenters
- /api/presenter/{id} - will retrieve presenter by id
