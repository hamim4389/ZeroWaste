package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminNotifications extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<RequestInfo> options;
     private FirebaseRecyclerAdapter<RequestInfo, FirebaeViewHolder> adapter;
    private DatabaseReference ref;

    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notifications);
        this.setTitle("Admin Notifications");

          recyclerView = findViewById(R.id.recyclerviewAdmin2);

            ref = FirebaseDatabase.getInstance().getReference().child("Requests");

              options = new FirebaseRecyclerOptions.Builder<RequestInfo>().setQuery(ref , RequestInfo.class).build();

                adapter = new FirebaseRecyclerAdapter< RequestInfo, FirebaeViewHolder>(options) {



                  @Override
                  public FirebaeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                      return new FirebaeViewHolder(LayoutInflater.from(AdminNotifications.this).inflate(R.layout.show_data,parent,false));
                  }

                  @Override
                  protected void onBindViewHolder(@NonNull FirebaeViewHolder holder, int i, @NonNull RequestInfo requestInfo) {
                      holder.description.setText("Description : "+ requestInfo.getDescription()) ;
                      holder.adress.setText("Adress : "+ requestInfo.getAdress()) ;
                      holder.contact_info.setText("Contact info  : "+ requestInfo.getContact() );
                  }
              };


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

              recyclerView.setAdapter(adapter);


    }
}
