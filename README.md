#  Hotel Reservation System

This project is created to show my ability to work with Core Java. The script helps user to track and modify the reservations of his hotel with custom number of rooms.
Project is realised using Singleton pattern. 

## Technologies: 

Java 17, Servlet, JDBC, Docker

## Launch:

Interaction with the service is implemented via web browser (http://localhost:8080/)

Program needs MySQL database to be set up:

Database is dockerized, run compose command in INFO/db-docker folder, this will configure DB container with some sample data:

`docker compose up`

Alternatively you can run .sql script located in db-docker/init folder and configure DB on your local server. 
Connectivity setting can be adjusted in db.properties file (localhost:3308 and root account used by default).


## Project status

actively developing

