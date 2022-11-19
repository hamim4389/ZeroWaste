package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername,etpassword;
    private Button bUserSignin,bAdminSignin,bSignup;
    private DatabaseReference ref;
    private boolean flag;
    private Member member;
    private String userName,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.editText2);
        etpassword = findViewById(R.id.editText);
        bUserSignin = findViewById(R.id.userButton);
        bAdminSignin = findViewById(R.id.adminButton);
        bSignup = findViewById(R.id.button2);




        bUserSignin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                userName = etUsername.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                userSignin();

            }
        });

        bAdminSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etUsername.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                adminSignin();
            }
        });

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
            })
                ;
        }

    private void adminSignin() {
        ref = FirebaseDatabase.getInstance().getReference("Admin");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                flag = false ;

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                     member = dataSnapshot1.getValue(Member.class);

                    Log.d("Creation","admin loop");

                    if (member.getUserName().equals(userName) && member.getPassword().equals(password))  {
                        flag = true ;


                        startActivity(new Intent(MainActivity.this,AdminHomePage.class));

                    }

                }
                if(flag == false){
                    Toast.makeText(getApplicationContext(),"Either you are not registered or you are not an admin",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void userSignin() {

        ref = FirebaseDatabase.getInstance().getReference("General User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             boolean flag = false ;

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    member = dataSnapshot1.getValue(Member.class);

                    if (member.getUserName().equals(userName) && member.getPassword().equals(password) ) {
                             flag = true ;

                             startActivity(new Intent(MainActivity.this,HomePage.class));

                    }

                }
                if( !flag ){
                    Toast.makeText(getApplicationContext(),"You are not registered, Please sign up first",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

