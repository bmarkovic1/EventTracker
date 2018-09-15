package com.example.boze.eventtracker;

public class Event {

    public String type;
    public String time;
    public double latitude;
    public double longitude;

    public String address;

    public Event() {
    }

    public Event(String type, String time, double latitude, double longitude, String address) {
        this.type = type;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }



    public String getType() { return type; }
    public String getTime() { return time; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAddress() { return address; }

    public void setType(String type) { this.type = type; }
    public void setTime(String time) { this.time = time; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setAddress(String address) { this.address = address; }
}