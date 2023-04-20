package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.LoginActivity;
import com.example.myapplication.activities.RegisterActivity;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();


    Context context;
    ArrayList<HomeVerModel> list;

    String uniqueId;
    public HomeVerAdapter(Context context,ArrayList<HomeVerModel> list){
        this.context= context;
        this.list= list;
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView name,price;
//        TextView name;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            imageView = itemview.findViewById(R.id.food1);
            name = itemview.findViewById(R.id.textView3);
            price = itemview.findViewById(R.id.textView7);
            itemview.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            df.child("cart").child(uniqueId).addListenerForSingleValueEvent(new ValueEventListener() {
                String itemName=name.getText().toString();
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        if(snapshot.child(itemName).exists())
                        {
                            int itemCount = Integer.parseInt(snapshot.child(itemName).child("itemCount").getValue().toString());
//                            df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
                            df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue(String.valueOf(itemCount+1));
                        }
                        else{
                            df.child("cart").child(uniqueId).child(itemName);
                            df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
                            df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
                        }
                    }
                    else{
                        df.child("cart").child(uniqueId).child(itemName);
                        df.child("cart").child(uniqueId).child(itemName).child("price").setValue(price.getText().toString());
                        df.child("cart").child(uniqueId).child(itemName).child("itemCount").setValue("1");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
            Log.d("click", "onClick: Click ho gaya ");
        }
    }
}
