package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BinStatus extends AppCompatActivity {

    TextView tvNasty,tvStolen,tvDamaged,tvFul,tv;
    Button yesButton,noButton;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_status);

        tvNasty = findViewById(R.id.tvNasty);
        tvFul = findViewById(R.id.tvFull);
        tvDamaged = findViewById(R.id.tvDamaged);
        tvStolen = findViewById(R.id.tvStolen);
        tv = findViewById(R.id.textView7);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        ref = FirebaseDatabase.getInstance().getReference("Bin Reports");

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
