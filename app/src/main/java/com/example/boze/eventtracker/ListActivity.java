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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    ListView EventList;
    EventAdapter eventAdapter;
    MapMarker marker;
    ArrayList<Event> events = new ArrayList<>();
    String Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        readData();

    }



    public String updateLocationText(MapMarker marker) {
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
                    return nearestAddress.getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "nepoznata adresa";
                }
        }
        return "nepoznata adresa";
    }

    private void readData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Events");

       /* myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                marker = dataSnapshot.getValue(MapMarker.class);

                events.add(new Event("Festival",4434,marker, updateLocationText(marker)));
                //loadEvent(marker, updateLocationText(marker) );
                setUpUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        myRef.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                marker = dataSnapshot.getValue(MapMarker.class);


                //loadEvent(marker, updateLocationText(marker));

                Date date = null;
                try {
                    date = getDate("2018-02-01");
                } catch (ParseException e) {
                    e.printStackTrace();

                }

                events.add(new Event("Policijska patrola",date,marker, updateLocationText(marker)));
                Log.d("TAG", "Value is: " + marker.getLatitude());
                setUpUI();
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

    private Date getDate(String dateString) throws ParseException {


        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(dateString);
        return date;


    }

    private void setUpUI() {
        this.EventList = (ListView) this.findViewById(R.id.event_list);
        this.eventAdapter = new EventAdapter(this.loadEvent());
        this.EventList.setAdapter(this.eventAdapter);

    }

    private ArrayList<Event> loadEvent() {


        return events;
    }
}