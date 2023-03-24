Title: Hotel Reservation System project

This project is created to show my ability to work with Core Java. The script helps user to track and modify the reservations of his hotel with custom number of rooms.
Project is realised using Singleton pattern. 

Technologies: Java 17

Launch:

In order to work properly program needs local MySQL database set up:

Using the command line:

If you do not already have the software, download MySQL Server and install the product.

From the MySQL command line, create the database and the table:

create database reserve;

create table reserve (
    reservationID int,
    roomNum int,
    startDay int,
    endDay int
);

Set up the connectivity setting in db.properties file (localhost:3306 and root account used by default)

Add mysql.connector.java library to your project structure.



Project status: actively developing

