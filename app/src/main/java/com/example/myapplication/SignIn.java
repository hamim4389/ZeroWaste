package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {

    private EditText etUsername,etmail,etpassword;
    private Button bSignup ;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private RadioGroup rOptions;
    private RadioButton rbn ;

    String userName,email,password,encodedEmail;
    Member member ;

    String UserType ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();


        rOptions = findViewById(R.id.radioGroup2);
        etUsername = findViewById(R.id.editText3);
        etmail = findViewById(R.id.editText4);
        etpassword = findViewById(R.id.editText5);
        bSignup = findViewById(R.id.button3);

        rOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbn = rOptions.findViewById(checkedId);

                switch (checkedId){
                    case R.id.adminRadioButton:
                        UserType = rbn.getText().toString().trim();
                        break;
                    case R.id.userRadioButton:
                        UserType = rbn.getText().toString().trim();
                        break;


                    default:
                }
            }
        });



        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();

            }
        });

    }

    private void userRegister() {


        userName = etUsername.getText().toString().trim();
        email = etmail.getText().toString().trim() ;
        password = etpassword.getText().toString().trim() ;




/*
        //checking the validity of the email
        if(email.isEmpty())
        {
            etmail.setError("Enter an email address");
            etmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etmail.setError("Enter a valid email address");
            etmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            etpassword.setError("Enter a password");
            etpassword.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            etpassword.setError("Enter a password");
            etpassword.requestFocus();
            return;
        }

 */


        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            ref = FirebaseDatabase.getInstance().getReference(UserType);

                            encodedEmail = encodeUserEmail(email);
                            member = new Member(userName,email,password);

                            ref.child(encodedEmail).setValue(member);

                            Toast.makeText(getApplicationContext(),"Registration is successful",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(SignIn.this, MainActivity.class));


                        } else {

                            Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });


    }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}
