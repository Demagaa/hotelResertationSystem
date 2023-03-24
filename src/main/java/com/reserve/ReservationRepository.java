package com.reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRepository {
    private Connection dbConnection;

    public ReservationRepository() {
        this.dbConnection = DButil.getConnection();
    }

    public void save(short startDay, short endDay, short roomNum, short reservationID) {
        try {
//            System.out.println(startDay + " " + endDay + " " + roomNum + " " + reservationID);
            PreparedStatement prepStatement = dbConnection.prepareStatement
                    ("insert into reserve (reservationID, roomNum,startDay,endDay) values (?, ?, ?, ?)");
            prepStatement.setShort(1, reservationID);
            prepStatement.setShort(2, roomNum);
            prepStatement.setShort(3, startDay);
            prepStatement.setShort(4, endDay);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public short check(short startDay, short endDay, short size) {
        short count = 1;
        try {
            PreparedStatement prepStatement = dbConnection.prepareStatement
                    ("select reservationID, roomNum,startDay,endDay from reserve");
            ResultSet rst = prepStatement.executeQuery();
            while (rst.next() && size >= count) {
                if ((rst.getShort(3) >= startDay && rst.getShort(3) <= endDay) ||
                        (rst.getShort(4) >= startDay && rst.getShort(4) <= endDay)) {
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > size) {
            return 0;
        } else {
            return count;
        }
    }
}
