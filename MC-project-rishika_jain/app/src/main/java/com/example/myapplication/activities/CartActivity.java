package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.cart.CartFragment;
import com.example.myapplication.ui.cart.EmptyCartFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();

    String uniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        SharedPreferences sharedPreferences = this.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");

        df.child("cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    if(snapshot.child(uniqueId).exists())
                    {
                        Toast.makeText(CartActivity.this, "inside", Toast.LENGTH_SHORT).show();
                        CartFragment fragment = new CartFragment(); // create an instance of your fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, fragment).commit();
                    }
                    else{
                        EmptyCartFragment fragment = new EmptyCartFragment(); // create an instance of your fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
                        Toast.makeText(CartActivity.this, "insideOut", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    EmptyCartFragment fragment = new EmptyCartFragment(); // create an instance of your fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
                    Toast.makeText(CartActivity.this, "outside", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}