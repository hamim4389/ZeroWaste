package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdminDashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Bin> arrayList;
    private FirebaseRecyclerOptions<Bin> options;
   // private FirebaseRecyclerAdapter<Bin, FirebaeViewHolder> adapter;
    private DatabaseReference ref;

   /* @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();
    }

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        this.setTitle("Admin Panel");

        recyclerView = findViewById(R.id.recyclerviewAdmin);

        arrayList = new ArrayList<Bin>();


        dataRetrieving();

       /* ref = FirebaseDatabase.getInstance().getReference().child("Bin Reports");

              options = new FirebaseRecyclerOptions.Builder<Bin>().setQuery(ref , Bin.class).build();

              adapter = new FirebaseRecyclerAdapter<Bin, FirebaeViewHolder>(options) {
                  @Override
                  protected void onBindViewHolder(@NonNull FirebaeViewHolder holder, int position, @NonNull Bin bin) {

                      holder.binId.setText("Bin Id : "+String.valueOf(bin.getBinId()) );
                      holder.nastyReports.setText("Nasty Reports : "+String.valueOf(bin.getNastyReports()) );
                      holder.fullReports.setText("Full Reports : "+String.valueOf(bin.getFullReports()) );
                      holder.damagedReports.setText("Damaged Reports : "+String.valueOf(bin.getDamagedReports()));
                      holder.stolenReports.setText("Stolen Reports : "+String.valueOf(bin.getStolenReports()));

                     /*
                      holder.itemView.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {

                          }
                      });
                      */

               /*   }

                  @NonNull
                  @Override
                  public FirebaeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                      return new FirebaeViewHolder(LayoutInflater.from(AdminDashboard.this).inflate(R.layout.show_data,viewGroup,false));
                  }
              };




              recyclerView.setAdapter(adapter);

                */
    }

    private void dataRetrieving() {

        ref = FirebaseDatabase.getInstance().getReference("Bin Reports");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() ) {
                    Bin bin = new Bin(dataSnapshot1.child("binId").getValue().toString(),
                            Integer.parseInt(dataSnapshot1.child("nastyReports").getValue().toString()),
                            Integer.parseInt(dataSnapshot1.child("fullReports").getValue().toString()),
                            Integer.parseInt(dataSnapshot1.child("damagedReports").getValue().toString()),
                            Integer.parseInt(dataSnapshot1.child("stolenReports").getValue().toString())
                            );
                    arrayList.add(bin);
                }

                dataSorting(arrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Data Retrieving Failed", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void dataSorting(ArrayList<Bin> arrayList) {

        Collections.sort(arrayList, new Comparator<Bin>() {
            @Override
            public int compare(Bin o1, Bin o2) {
                return o2.getTotal() - o1.getTotal();
            }
        });

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(arrayList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}
