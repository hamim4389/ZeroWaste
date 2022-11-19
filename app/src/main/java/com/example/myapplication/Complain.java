package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class Complain extends AppCompatActivity {

    private EditText etBinId;
    private RadioGroup rOption;
    private RadioButton rb ;

    DatabaseReference ref;

    private int fullReports = 0 , nastyReports  = 0, damagedReports = 0 ,stolenReports = 0 ;
    private Button bSubmit ;
    private String binStatus ;
    private String binId ;
    private String checked ;
    private int reports = 0;

    //AlertDialog.Builder builder1 = new AlertDialog.Builder(Complain.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        etBinId = findViewById(R.id.etBinId);


        bSubmit = findViewById(R.id.bSubmit);

        rOption = findViewById(R.id.radioGroup);
        ref = FirebaseDatabase.getInstance().getReference("Bin Reports");

        rOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = rOption.findViewById(checkedId);

                switch (checkedId){
                    case R.id.rNasty:
                        binStatus = rb.getText().toString().trim();
                        checked ="nastyReports" ;
                        nastyReports++;
                        break;
                    case R.id.rFull:
                        binStatus = rb.getText().toString().trim();
                        checked ="fullReports" ;
                        fullReports++;
                        break;
                    case R.id.rDamaged:
                        binStatus = rb.getText().toString().trim();
                        checked ="damagedReports" ;
                        damagedReports++;
                        break;
                    case R.id.rStolen:
                        binStatus = rb.getText().toString().trim();
                        checked ="stolenReports" ;
                        stolenReports++;
                        break;

                     default:
                }
            }
        });


        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myComplain();
            }
        });




    }

    private void myComplain() {


        binId = etBinId.getText().toString().trim();




      //  Bin bin = new Bin(binId  ,nastyReports , fullReports , damagedReports, stolenReports );



        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(binId)){

                        String current = (String) dataSnapshot.child(binId).child(checked).getValue().toString();
                        //String tReports = (String) dataSnapshot.child(binId).child("totalReports").getValue().toString();
                        int count = Integer.parseInt(current);
                        //totalReports = Integer.parseInt(tReports);

                        count ++ ;
                        //totalReports++;

                        ref.child(binId).child(checked).setValue(count);
                        //ref.child(binId).child("totalReports").setValue(totalReports);

                        checked = "";

                  /*  String full = (String) dataSnapshot.child(binId).child("fullReports").getValue().toString() ;
                    int fullCount = Integer.parseInt(full);
                    fullCount+=fullReports;
                    ref.child(binId).child("fullReports").setValue(fullCount);

                    String damaged = (String) dataSnapshot.child(binId).child("damagedReports").getValue().toString();
                    int damagedCount = Integer.parseInt(damaged);
                    damagedCount+=damagedReports;
                    ref.child(binId).child("damagedReports").setValue(damagedCount);

                    String stolen = (String) dataSnapshot.child(binId).child("stolenReports").getValue().toString();
                    int stolenCount = Integer.parseInt(stolen);
                    stolenCount+=stolenReports;
                    ref.child(binId).child("stolenReports").setValue(stolenCount);

                   */


//                       Bin bin1 = dataSnapshot.getValue(Bin.class);
//
//                           nastyReports = nastyReports +  bin1.getNastyReports() ;
//                           fullReports = fullReports + bin1.getFullReports() ;
//                           damagedReports = damagedReports + bin1.getDamagedReports() ;
//                           stolenReports = stolenReports + bin1.getStolenReports() ;
//
//                       Bin bin = new Bin(binId, nastyReports , fullReports , damagedReports , stolenReports );
                    Toast.makeText(getApplicationContext(),"info has added",Toast.LENGTH_SHORT).show();

                  //  startActivity(new Intent(Complain.this, AdminDashboard.class));

//                    ref.child(binId).setValue(bin);

                }
                else {
                   // totalReports = fullReports + nastyReports + damagedReports + stolenReports ;
                    Bin bin = new Bin(binId  ,nastyReports ,fullReports , damagedReports, stolenReports);
                    ref.child(binId).setValue(bin);
                    Toast.makeText(getApplicationContext(),"info added",Toast.LENGTH_SHORT).show();
                }

               // Toast.makeText(getApplicationContext(),"info added",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Error occurred",Toast.LENGTH_LONG).show();

            }
        });
        //Toast.makeText(getApplicationContev xt(),"info added",Toast.LENGTH_SHORT).show();

        etBinId.setText("");
        rOption.clearCheck();

      /*  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() ){
                    Bin bin = dataSnapshot1.getValue(Bin.class);
                    if(bin.getBinId() == binId){

                        flag = false ;
                        Bin bin1 = new Bin(binId , binStatus,bin.getReports()+1, bin.getBinDatabaseKey() );
                        ref.child( bin.getBinDatabaseKey() ).setValue(bin1) ;
                        Toast.makeText(getApplicationContext(),"heyyyyy",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"info added",Toast.LENGTH_SHORT).show();

                        etBinId.setText("");
                        break;
                    }
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




       */

















    }
}
