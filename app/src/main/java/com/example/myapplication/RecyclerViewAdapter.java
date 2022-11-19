package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Bin> binList ;


   // private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private Context context  ;
    View view ;



    public RecyclerViewAdapter(ArrayList<Bin>  binList, Context mContext) {
        this.binList =  binList;

        this.mContext = mContext;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.newshow_data, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
//
//        Glide.with(mContext)
//                .asBitmap()
//                .load(binList.get(position))
//                .into(holder.image);

        holder.binId.setText("Bin Adress "+ binList.get(position).binId);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked on "+ binList.get(position).binId);
                /*Intent newintent = new Intent();
                context = view.getContext();
                newintent.setClass(context,BinStatus.class);
                context.startActivity(newintent);

                 */

                Intent intent = new Intent(mContext, BinStatus.class).putExtra("hi",5);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);


            }
        });




    }

    @Override
    public int getItemCount() {
        return binList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      //  CircleImageView image;
        ImageView image;
        TextView binId;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            binId = itemView.findViewById(R.id.bin_id);
            parentLayout = itemView.findViewById(R.id.parent_layout);


        }
    }
}