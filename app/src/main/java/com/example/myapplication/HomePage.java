package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomePage extends AppCompatActivity {

    private LinearLayout bComplain,bRequest,bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
           this.setTitle("Dashboard");
        bComplain =findViewById(R.id.complain);
        bRequest = findViewById(R.id.request_b);
        bMap = findViewById(R.id.map_b);
       // bRequest = findViewById(R.id.requ);

        bComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, Complain.class));
            }
        });

        bRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, RequestActivity.class));
            }
        });
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,Map.class));
            }
        });

    }
}
