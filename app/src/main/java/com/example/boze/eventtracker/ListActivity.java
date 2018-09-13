package com.example.boze.eventtracker;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    ListView EventList;
    EventAdapter eventAdapter;
    MapMarker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        setUpUI();
        readData();

    }

    public void updateLocationText(MapMarker marker) {
        if(Geocoder.isPresent()){
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> nearByAddresses = geocoder.getFromLocation(
                        marker.getLatitude(), marker.getLongitude(),1);
                if(nearByAddresses.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Address nearestAddress = nearByAddresses.get(0);
                    stringBuilder.append(nearestAddress.getAddressLine(0)).append("\n")
                            .append(nearestAddress.getLocality()).append("\n")
                            .append(nearestAddress.getCountryName());
                    Log.d("TAG", "Value is: " + nearByAddresses);

                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void readData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Events");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                marker = dataSnapshot.getValue(MapMarker.class);

                updateLocationText(marker);
                Log.d("TAG", "Value is: " + marker.getLatitude());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                MapMarker marker = dataSnapshot.getValue(MapMarker.class);

                Log.d("TAG", "Value is: " + marker.getLatitude());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void setUpUI() {
        this.EventList = (ListView) this.findViewById(R.id.event_list);
        this.eventAdapter = new EventAdapter(this.loadEvents());
        this.EventList.setAdapter(this.eventAdapter);

    }

    private ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<>();
        MapMarker marker = new MapMarker(123,456,234);
        events.add(new Event("Koncert",4434,marker));
        events.add(new Event("Policijska patrola",4434, marker));
        events.add(new Event("Akcijska cijena pića",4434, marker));
        events.add(new Event("Festival",4434, marker));
        events.add(new Event("A game of thrones",4434, marker));
        events.add(new Event("A clash of kings",4434, marker));
        events.add(new Event("A storm of swords",4434, marker));
        events.add(new Event("A feast for crows",4434, marker));
        events.add(new Event("A dance with dragons",4434, marker));
        events.add(new Event("The winds of winter",34343, marker));
        events.add(new Event("A dream of spring",343, marker));
        return events;
    }
}