package com.reserve;

public class Reservation {

    private String inputStart;
    private String inputEnd;
    private short startDay;
    private short endDay;
    private short roomNum;

    public String getInputStart() {
        return inputStart;
    }

    public String getInputEnd() {
        return inputEnd;
    }

    public void setInputEnd(String inputEnd) {
        this.inputEnd = inputEnd;
    }

    public void setInputStart(String inputDate) {
        this.inputStart = inputDate;
    }

    public void setStartDay(short startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(short endDay) {
        this.endDay = endDay;
    }

    public void setRoomNum(short roomNum) {
        this.roomNum = roomNum;
    }

    public short getStartDay() {
        return startDay;
    }

    public short getEndDay() {
        return endDay;
    }

    public short getRoomNum() {
        return roomNum;
    }

}

