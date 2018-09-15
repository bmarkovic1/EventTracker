package com.example.boze.eventtracker;


public class MapMarker {

    public String type;
    public String time;
    public double latitude;
    public double longitude;


    public MapMarker() {
    }

    public MapMarker(String type, String time, double latitude, double longitude) {

        this.type = type;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}