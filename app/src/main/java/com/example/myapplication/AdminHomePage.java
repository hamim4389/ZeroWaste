package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AdminHomePage extends AppCompatActivity {

    LinearLayout breq,bhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home_page);

        breq = findViewById(R.id.button100);
        bhome = findViewById(R.id.button1);

        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this,AdminDashboard.class));
            }
        });

        breq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this,AdminNotifications.class));
            }
        });
    }
}
