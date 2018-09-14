package com.example.boze.eventtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_event extends Activity {

    Button add_new, pickLocation;
    EditText etType, etDate, etMonth, etYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);
        setUpUI();
        initClickListener();

    }

    private void initClickListener() {



        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //*if su obrasci popunjeni
                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Events");
                MapMarker marker = new MapMarker(123456, 45.554961666666, 18.695513333);
                mRef.push().setValue(marker);
            }
        });

    }

    private void setUpUI() {

        add_new=(Button)findViewById(R.id.add_new);
        //pickLocation=(Button)findViewById(R.id.addLocationOnMap);
        etType = (EditText) findViewById(R.id.etEventType);
        etDate = (EditText) findViewById(R.id.etEventDate);
        etMonth = (EditText) findViewById(R.id.etEventMonth);
        etYear = (EditText) findViewById(R.id.etEventYear);
    }
}
