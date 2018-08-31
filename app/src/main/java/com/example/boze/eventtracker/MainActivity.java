package com.example.boze.eventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button map, add, list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
        initClickListener();
    }

    private void initClickListener() {
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_event.class);
                startActivity(intent);
            }
        });
    }

    private void setUpUI() {
        map=(Button)findViewById(R.id.map);
        add=(Button)findViewById(R.id.events);
        list=(Button)findViewById(R.id.events_list);
    }
}