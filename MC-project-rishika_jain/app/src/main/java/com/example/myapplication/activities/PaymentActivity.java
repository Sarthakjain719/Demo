package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends AppCompatActivity {
    String uniqueId;

    String BalanceLeft;

    int totalAmount;


    TextView Amount;
    TextView Balance;

    Button placeOrder;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference df=database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        SharedPreferences sharedPreferences = this.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        uniqueId = sharedPreferences.getString("uniqueId", "");

        Intent intent=getIntent();
        totalAmount=intent.getIntExtra("amount",0);

        Amount = this.findViewById(R.id.textView);
        Balance=this.findViewById(R.id.textView5);
        placeOrder=this.findViewById(R.id.button4);

        df.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BalanceLeft=snapshot.child(uniqueId).child("balance").getValue(String.class);
                Log.i("Balance: ",BalanceLeft);
                Balance.setText("Available Balance : "+ BalanceLeft);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Amount.setText("Amount to be paid : "+totalAmount);

       placeOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(Integer.parseInt(BalanceLeft)>=totalAmount)
               {
                   Toast.makeText(PaymentActivity.this, "Order Successful.", Toast.LENGTH_SHORT).show();
                   df.child("users").child(uniqueId).child("balance").setValue(String.valueOf(Integer.parseInt(BalanceLeft)-totalAmount));

                   df.child("orderDetail").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {

                              df.child("cart").addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                                      for (DataSnapshot uniqueIdSnapshot : snapshot.child(uniqueId).getChildren()) {
//
                                              String itemName=uniqueIdSnapshot.getKey();
                                              String itemCount = uniqueIdSnapshot.child("itemCount").getValue(String.class);
                                              String itemPrice = uniqueIdSnapshot.child("price").getValue(String.class);
//                                            df.child("orderDetail").child(uniqueId).child("itemName").setValue(itemName);
                                              df.child("orderDetail").child(uniqueId).child(itemName).child("itemCount").setValue(itemCount);

                                      }
                                      df.child("cart").child(uniqueId).removeValue()
                                              .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                  @Override
                                                  public void onSuccess(Void aVoid) {
                                                      // The key was successfully removed from the cart node
                                                  }
                                              })
                                              .addOnFailureListener(new OnFailureListener() {
                                                  @Override
                                                  public void onFailure(@NonNull Exception e) {
                                                      // Handle the error
                                                  }
                                              });
                                  }
                                  @Override
                                  public void onCancelled(@NonNull DatabaseError error) {
                                   }



                              });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });


               }
               else{
                   Toast.makeText(PaymentActivity.this, "Insufficient Balance. Try Other Payment mode", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }
}