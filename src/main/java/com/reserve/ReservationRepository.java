package com.reserve;
import java.sql.*;


public class ReservationRepository {

    static DButil util = new DButil();

    private static String dbDriver = util.getDbDriver();
    private static final String URL = util.getConnectionUrl();
    private static final String USERNAME = util.getUserName();
    private static final String PASSWORD = util.getPassword();

    private static Connection connection;


    // Connecting to database //
    private void setConnection(){
        try {
            Class.forName(util.getDbDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(short startDay, short endDay, short roomNum) {
        try {
            setConnection();

            PreparedStatement prepStatement = connection.prepareStatement
                    ("insert into reserve (roomNum,startDay,endDay) values (?, ?, ?)");
            prepStatement.setShort(1, roomNum);
            prepStatement.setShort(2, startDay);
            prepStatement.setShort(3, endDay);
            prepStatement.executeUpdate();

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public short check(short startDay, short endDay, short size) {
        short count = 1;
        try {
            setConnection();

            PreparedStatement prepStatement = connection.prepareStatement
                    ("select roomNum,startDay,endDay from reserve");
            ResultSet rst = prepStatement.executeQuery();
            while (rst.next() && size >= count) {
                if (rst.getShort(2) >= startDay && rst.getShort(3) <= endDay) {
                    count++;
                }
            }
            closeConnection();
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
