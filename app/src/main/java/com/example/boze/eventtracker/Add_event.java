package com.example.boze.eventtracker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Add_event extends Activity implements OnMapReadyCallback {

    Button addNew, pickLocation;
    EditText etDate, etMonth, etYear;
    AutoCompleteTextView actvType;
    String TypeInput, sDayInput, sMonthInput, sYearInput;
    double pickedLatitude, pickedLongitude;
    boolean locationPickedFlag=false;
    boolean eventAdded = false;

    GoogleMap mGoogleMap;
    MapFragment mMapFragment;
    private GoogleMap.OnMapClickListener mCustomOnMapClickListener;
    static public final int REQUEST_LOCATION = 1;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        UiSettings uiSettings = this.mGoogleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        this.mGoogleMap.setOnMapClickListener(this.mCustomOnMapClickListener);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Add_event.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            return;
        }
        this.mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);
        setUpUI();
        initClickListener();
        this.initialize();
    }

    private void initialize() {
        this.mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fGoogleMap);
        this.mMapFragment.getMapAsync(this);
        this.mCustomOnMapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(!locationPickedFlag) {
                    locationPickedFlag = true;
                    pickedLatitude = latLng.latitude;
                    pickedLongitude = latLng.longitude;
                    MarkerOptions newMarkerOptions = new MarkerOptions();
                    newMarkerOptions.position(latLng);
                    mGoogleMap.addMarker(newMarkerOptions);
                }
            }
        };
    }

    private void initClickListener() {

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInputs() && !eventAdded){
                    eventAdded = true;
                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Events");
                    String date = sDayInput + "-" + sMonthInput + "-" + sYearInput;
                    MapMarker marker = new MapMarker(TypeInput, date, pickedLatitude, pickedLongitude);
                    mRef.push().setValue(marker);
                    Toast.makeText(Add_event.this, "Event added",
                            Toast.LENGTH_LONG).show();


                }
            }
        });

    }

    private boolean checkInputs() {
        TypeInput = actvType.getText().toString();
        if(TypeInput.matches(""))
        {
            Toast.makeText(Add_event.this, "Event not filled",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        sDayInput = etDate.getText().toString();
        if(sDayInput.matches(""))
        {
            Toast.makeText(Add_event.this, "Day not filled",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        sMonthInput = etMonth.getText().toString();
        if(sMonthInput.matches(""))
        {
            Toast.makeText(Add_event.this, "Month not filled",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        sYearInput = etYear.getText().toString();
        if(sYearInput.matches(""))
        {
            Toast.makeText(Add_event.this, "Year not filled",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        int DayInput = Integer.parseInt(etDate.getText().toString());

        if(DayInput > 31 || DayInput < 1)
        {
            Toast.makeText(Add_event.this, "Invalid Day value",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        int MonthInput = Integer.parseInt(etMonth.getText().toString());

        if(MonthInput > 12 || MonthInput < 1)
        {
            Toast.makeText(Add_event.this, "Invalid Month value",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        int YearInput = Integer.parseInt(etYear.getText().toString());

        if(YearInput > 2030 || YearInput < 2018)
        {
            Toast.makeText(Add_event.this, "Invalid Year value",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void setUpUI() {

        addNew=(Button)findViewById(R.id.add_new);
        actvType = (AutoCompleteTextView) findViewById(R.id.actvEventType);
        etDate = (EditText) findViewById(R.id.etEventDate);
        etMonth = (EditText) findViewById(R.id.etEventMonth);
        etYear = (EditText) findViewById(R.id.etEventYear);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, EVENTS);
        actvType.setAdapter(adapter);

    }
    private static final String[] EVENTS = new String[] {
            "POLICIJSKA PATROLA","KONCERT", "AKCIJSKA CIJENA PIĆA","FESTIVAL"
    };
}