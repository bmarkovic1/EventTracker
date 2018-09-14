package com.example.boze.eventtracker;

import java.util.Date;

public class Event {

    private String mType;
    private Date mDate;
    private MapMarker mMarker;
    private String mAddress;
    public Event(String type, Date date, MapMarker marker, String Address) {
        mType = type;
        mDate = date;
        mMarker = marker;
        mAddress = Address;
    }
    public String getType() { return mType; }
    public Date getTime() { return mDate; }
    public MapMarker getMarker() { return mMarker; }
    public String getAddress() { return mAddress; }
}