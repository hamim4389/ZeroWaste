package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaeViewHolder extends RecyclerView.ViewHolder {

   public TextView description,adress,contact_info;

    public FirebaeViewHolder(@NonNull View itemView) {
        super(itemView);

        description = itemView.findViewById(R.id.binID);

        adress= itemView.findViewById(R.id.full);
        contact_info = itemView.findViewById(R.id.damaged);



    }
}
