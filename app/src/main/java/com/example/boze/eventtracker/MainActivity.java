package com.example.boze.eventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button allEvents, myEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
        initClickListener();
    }

    private void initClickListener() {
        allEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });
    }

    private void setUpUI() {
        allEvents=(Button)findViewById(R.id.toAllEventsInACity);
        myEvents=(Button)findViewById(R.id.EventsYouWantToParticipate);
    }
}