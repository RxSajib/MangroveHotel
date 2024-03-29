package com.hotel.mangrovehotel;

public class MessageHolder {

    String date;
    String from;
    String message;
    String message_PushID;
    String sendertime;
    String time;
    String to;
    String type;

    public MessageHolder() {

    }

    public MessageHolder(String date, String from, String message, String message_PushID, String sendertime, String time, String to, String type) {
        this.date = date;
        this.from = from;
        this.message = message;
        this.message_PushID = message_PushID;
        this.sendertime = sendertime;
        this.time = time;
        this.to = to;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_PushID() {
        return message_PushID;
    }

    public void setMessage_PushID(String message_PushID) {
        this.message_PushID = message_PushID;
    }

    public String getSendertime() {
        return sendertime;
    }

    public void setSendertime(String sendertime) {
        this.sendertime = sendertime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
