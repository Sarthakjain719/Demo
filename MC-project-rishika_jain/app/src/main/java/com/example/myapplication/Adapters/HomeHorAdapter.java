package com.example.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.HomeHorModel;
import com.example.myapplication.models.HomeVerModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference df=database.getReference();

    UpdateVertical updateVertical;

    Activity activity;
    ArrayList<HomeHorModel> list;
    boolean check = true;
    boolean select=true;
    int row_index = -1;


    public HomeHorAdapter(UpdateVertical updateVertical, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVertical = updateVertical;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        if(check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.paneer_sandwich, "Paneer Sandwich", "Rs.60"));
            homeVerModels.add(new HomeVerModel(R.drawable.aalo_sandwich, "Aalo Sandwich", "Rs.40"));
            homeVerModels.add(new HomeVerModel(R.drawable.butter_maggi, "Butter Maggi", "Rs.35"));
            homeVerModels.add(new HomeVerModel(R.drawable.veg_maggi, "Veg Maggi", "Rs.35"));
            homeVerModels.add(new HomeVerModel(R.drawable.egg_maggi, "Egg Maggi", "Rs.40"));
            homeVerModels.add(new HomeVerModel(R.drawable.plain_maggi, "Plain Maggi", "Rs.25"));
            homeVerModels.add(new HomeVerModel(R.drawable.mixed_paratha, "Mixed Paratha", "Rs.30"));
            homeVerModels.add(new HomeVerModel(R.drawable.aalo_paratha, "Aalo Paratha", "Rs.30"));
            homeVerModels.add(new HomeVerModel(R.drawable.paneer_paratha, "Paneer Paratha", "Rs.40"));

            updateVertical.callback(position, homeVerModels);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index = position;
                    notifyDataSetChanged(); 
                    if(position==0){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.paneer_sandwich,"Paneer Sandwich","Rs.60"));
                        homeVerModels.add(new HomeVerModel(R.drawable.aalo_sandwich,"Aalo Sandwich","Rs.40"));
                        homeVerModels.add(new HomeVerModel(R.drawable.butter_maggi,"Butter Maggi","Rs.35"));
                        homeVerModels.add(new HomeVerModel(R.drawable.veg_maggi,"Veg Maggi","Rs.35"));
                        homeVerModels.add(new HomeVerModel(R.drawable.egg_maggi,"Egg Maggi","Rs.40"));
                        homeVerModels.add(new HomeVerModel(R.drawable.plain_maggi,"Plain Maggi","Rs.25"));
                        homeVerModels.add(new HomeVerModel(R.drawable.mixed_paratha,"Mixed Paratha","Rs.30"));
                        homeVerModels.add(new HomeVerModel(R.drawable.aalo_paratha,"Aalo Paratha","Rs.30"));
                        homeVerModels.add(new HomeVerModel(R.drawable.paneer_paratha,"Paneer Paratha","Rs.40"));
                        updateVertical.callback(position,homeVerModels);
                    } else if (position==1) {
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.strawberry_mojito,"Strawberry Mojito","Rs.65"));
                        homeVerModels.add(new HomeVerModel(R.drawable.paan_mojito,"Paan Mojito","Rs.65"));
                        homeVerModels.add(new HomeVerModel(R.drawable.latte,"Iced Latte","Rs.80"));
                        homeVerModels.add(new HomeVerModel(R.drawable.cappuccino,"Iced Cappuccino","Rs.80"));
                        homeVerModels.add(new HomeVerModel(R.drawable.veg_pasta,"Veg Pasta","Rs.75"));
                        homeVerModels.add(new HomeVerModel(R.drawable.non_veg_pasta,"Non Veg Pasta","Rs.100"));
                        homeVerModels.add(new HomeVerModel(R.drawable.masala_soda,"Masala Soda","Rs.65"));
                        homeVerModels.add(new HomeVerModel(R.drawable.fresh_lime_soda,"Fresh Lime Soda","Rs.65"));

                        updateVertical.callback(position,homeVerModels);
                    }
                    else if (position==2) {
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.mysore_masala_dosa,"Mysore Masala Dosa","Rs.90"));
                        homeVerModels.add(new HomeVerModel(R.drawable.butter_dosa,"Butter Dosa","Rs.75"));
                        homeVerModels.add(new HomeVerModel(R.drawable.paneer_dosa,"Paneer Dosa","Rs.100"));
                        homeVerModels.add(new HomeVerModel(R.drawable.plain_dosa,"Plain Dosa","Rs.70"));
                        homeVerModels.add(new HomeVerModel(R.drawable.uttapam,"Uttapam","Rs.70"));
                        homeVerModels.add(new HomeVerModel(R.drawable.idli,"Idli","Rs.60"));
                        homeVerModels.add(new HomeVerModel(R.drawable.vadaa,"Vada","Rs.70"));

                        updateVertical.callback(position,homeVerModels);
                    }
                }
            });

            if(select){
                if(position==0)
                {
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select=false;
                }
            }
            else
            {
                if(row_index==position){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }else
                {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }

        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.res1);
            name = itemView.findViewById(R.id.canteen_name);
            cardView = itemView.findViewById(R.id.card1);
        }


        @Override
        public void onClick(View view) {
            int position=this.getAdapterPosition();
            Toast.makeText(activity, position, Toast.LENGTH_SHORT).show();
            Log.d("click", "onClick: Click ho gaya ");
        }
    }
}