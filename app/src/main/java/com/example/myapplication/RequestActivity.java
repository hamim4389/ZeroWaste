package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {
    EditText etDescription,etAdress,etContact;
    Button buttonSubmit;
    String des,ad,con;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        etDescription = findViewById(R.id.editText6);
        etAdress = findViewById(R.id.editText9);
        etContact = findViewById(R.id.editText8);
        buttonSubmit = findViewById(R.id.button_req);

        ref = FirebaseDatabase.getInstance().getReference("Requests");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRequest();
            }
        });



    }

    private void myRequest() {
        des = etDescription.getText().toString().trim();
        ad = etAdress.getText().toString().trim();
        con = etContact.getText().toString().trim();
        RequestInfo info = new RequestInfo(des, ad, con) ;
        ref.child(con).setValue(info);
        Toast.makeText(getApplicationContext(),"Request sent", Toast.LENGTH_LONG);

        etDescription.setText("");
        etAdress.setText("");
        etContact.setText("");

        startActivity(new Intent(RequestActivity.this, HomePage.class));
    }
}
