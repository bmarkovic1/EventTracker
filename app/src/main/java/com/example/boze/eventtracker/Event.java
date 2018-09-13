package com.example.boze.eventtracker;

public class Event {

    private String mType;
    private int mTime;
    private MapMarker mMarker;
    public Event(String type, int time, MapMarker marker) {
        mType = type;
        mTime = time;
        mMarker = marker;
    }
    public String getType() { return mType; }
    public int getTime() { return mTime; }
    public MapMarker getMarker() { return mMarker; }
}