package com.reserve;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/redirect.html")
public class Servlet extends HttpServlet {
    private final short[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String hotelName;
    private short size;
    private boolean inputFormatCheck;
    short reservationID = 0;
    private ReservationRepository ReservationRepository;

    public Servlet() {
        super();
        this.ReservationRepository = new ReservationRepository();
        getHotelProperties();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reservation reservation = new Reservation();
        reservation.setInputStart(request.getParameter("reservationStart"));
        reservation.setInputEnd(request.getParameter("reservationEnd"));

        checkDateFormat(reservation.getInputStart(), reservation.getInputEnd());
        if (ReservationRepository != null && inputFormatCheck) {
            reservation.setStartDay(timeRefactor(reservation.getInputStart()));
            reservation.setEndDay(timeRefactor(reservation.getInputEnd()));
            if (!checkDateLimits(reservation.getStartDay(), reservation.getEndDay())) {
                response.sendRedirect("failure.jsp");
                return;
            }
            reservation.setRoomNum(ReservationRepository.check(reservation.getStartDay(), reservation.getEndDay(), size));

            if (reservation.getRoomNum() == 0) {
                response.sendRedirect("noRooms.jsp");
                return;
            }
            try {
                ReservationRepository.save(reservation.getStartDay(), reservation.getEndDay(), reservation.getRoomNum(), reservationID++);
                RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
                request.setAttribute("RoomNum", reservation.getRoomNum()); // set your String value in the attribute
                request.setAttribute("hotelName", hotelName);
                dispatcher.forward(request, response);

            } catch (Exception e) {
            }
        } else {
            response.sendRedirect("failure.jsp");
        }

    }

    private short timeRefactor(String date) {
        short day = Short.parseShort((date.substring(8, 10)));
        int month = Short.parseShort(date.substring(5, 7));
        for (int i = 1; i < month; i++) {
            day += months[i];
        }
        return day;
    }

    private void getHotelProperties() {
        try {
            InputStream inputStream = DButil.class.getClassLoader().getResourceAsStream("hotelProperties.properties");
            Properties properties = new Properties();
            if (properties != null) {
                properties.load(inputStream);
                size = Short.parseShort(properties.getProperty("size"));
                hotelName = properties.getProperty("hotelName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkDateFormat(String dateStart, String dateEnd) {
        if (dateStart.length() > 0 && dateEnd.length() > 0) {
            inputFormatCheck = true;
            return;
        }
        inputFormatCheck = false;
    }

    public boolean checkDateLimits(short startDay, short endDay) {
        short currentTime = timeRefactor(String.valueOf(java.time.LocalDate.now()));
        if (startDay > endDay || currentTime > startDay) {
            return false;
        }
        return true;
    }
}

