//package com.example.myapplication.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.TextView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.activities.LoginActivity;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
////        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//        TextView t1 = (TextView) findViewById(R.id.textView12);
//        t1.setOnClickListener(view->{
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        });
//    }
//}
package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    //private FirebaseAuth auth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference df=database.getReference();

    EditText username;
    EditText phoneno;
    EditText passwordtext;

    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        username=findViewById(R.id.editTextTextPersonName);
        phoneno=findViewById(R.id.editTextTextPersonName2);
        passwordtext=findViewById(R.id.editTextTextPersonName3);
        register=findViewById(R.id.button3);
        // auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonetxt=phoneno.getText().toString();
                String pass=passwordtext.getText().toString();
                String fullname=username.getText().toString();
                if(phonetxt.isEmpty() || pass.isEmpty() || fullname.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please filled the Field", Toast.LENGTH_SHORT).show();
                }
                else{
                    df.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonetxt)){
                                Toast.makeText(RegisterActivity.this, "EmailId is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                df.child("users").child(phonetxt).child("fullname").setValue(fullname);
                                df.child("users").child(phonetxt).child("password").setValue(pass);
                                df.child("users").child(phonetxt).child("balance").setValue("0");


                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                    auth.createUserWithEmailAndPassword(emailId,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful())
//                            {
//                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
//                            }
//                            else{
//                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful.Try Again", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                }
            }
        });

        TextView t1 = (TextView) findViewById(R.id.textView12);
        t1.setOnClickListener(view->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}