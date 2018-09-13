package com.example.boze.eventtracker;


public class MapMarker {

    public double timestamp;
    public double latitude;
    public double longitude;


    public MapMarker() {
    }

    public MapMarker(double timestamp, double latitude, double longitude) {

        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}