package com.reserve;

/**
 * Author: Aleksei Chursin
 * Name of the project: Hotel Reservation System
 */

import java.util.Scanner;

public class Hotel {
    short size;
    private short start_day;
    short end_day;
    short max_day = 365;
    short [][] reservation; //array for keeping information about recent reservations
    boolean availability; //availability of room
    short [] check  = new short[365]; //additional array for control availability of room
    boolean occupied_room;
    boolean reservation_made = false;

    Hotel (short size){
        this.size = size;
    }

    /**
     * Function is doing the reservation in hotel
     * @param size - size of the hotel, can be <=1000
     * @return Function type is void, return nothing
     */
    void hotel_reservation() {
        Scanner data = new Scanner(System.in);
        char client_answer = 'n';
        short number_of_room = 0;
        reservation = new short [size][max_day];
        do {
            System.out.print("Please pick the arrival day to our hotel: ");
            start_day = (short) data.nextInt();
            while (start_day < 0) {
                System.out.print("Reservation cannot be made for overpast period, please try again: ");
                start_day = (short) data.nextInt();
            }
            System.out.print("Please pick the departure day: ");
            end_day = (short) data.nextInt();
            while (end_day > 365) {
                System.out.print("Reservation cannot be made for period longer than one year (365 days). Please, choose earlier date: ");
                end_day = (short) data.nextInt();
            }
            while (start_day > end_day) {
                System.out.print("Reservation cannot be made: arrival day is later than departure day. Please choose the arrival day: ");
                start_day = (short) data.nextInt();
                System.out.print("Please choose the departure day: ");
                end_day = (short) data.nextInt();
            }
            System.out.println("Arrival and departure days received. Application is going to check if there is a free room for you on following dates");

            //Here we will check availability of room
            short i; //Using following variables to count for cycles
            short j;

            this.reservation_made = false;
            for (i = 0; i < size; i++) {
                occupied_room = false;
                for (j = start_day; j <= end_day; j++) {
                    check[j] = reservation[i][j];
                    if (check[j] == 1) { //if variable == 1, then room is occupied
                        occupied_room = true; //occupied room was found
                        availability = false;
                        break;
                    }
                }
                if (!occupied_room && !reservation_made) { //room is free, availability = true, then we can do the reservation
                    availability = true;
                    number_of_room = i;
                    reservation_made = true;
                    break;
                }
            }

            //Here we will do reservation at room
            if (availability) {
                for (j = start_day; j <= end_day; j++) {
                    reservation[number_of_room][j] = 1;
                }
                System.out.print("Reservation for room number " + (number_of_room + 1) + " was confirmed, would you like to make another one? (y/n): ");
                client_answer = data.next().charAt(0);
            }
            else {
                System.out.print("Sorry, all rooms are occupied. Would you like to change the date? (y/n): ");
                client_answer = data.next().charAt(0);
            }
        } while (client_answer == 'y');
        //This part of the code is used to print reservation log
        short a = 0;
        while (a < size) {
            short b = 0;
            while (b < max_day) {
                System.out.print(reservation[a][b] + " ");
                b++;
            }
            System.out.println("\n");
            a++;
        }
    }
}


