//package com.example.myapplication.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.ui.home.HomeFragment;
//
//public class LoginActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
////        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//        TextView t1 = (TextView) findViewById(R.id.textView24);
//        t1.setOnClickListener(view->{
//            Intent intent = new Intent(this, RegisterActivity.class);
//            startActivity(intent);
//
//        });
//
//        Button b1 = (Button) findViewById(R.id.button8);
//        b1.setOnClickListener(view->{
////            Intent intent = new Intent(this, HomeFragment.class);
////            startActivity(intent);
////            Bundle bundle = new Bundle();
//            b1.setVisibility(View.GONE);
//            if(getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().size()>0)
//            {
//                for(int i=0;i<getSupportFragmentManager().getFragments().size();i++)
//                {
//                    Fragment fragment = getSupportFragmentManager().getFragments().get(i);
//                    if(fragment!=null)
//                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                }
//            }
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            HomeFragment firstFragment = new HomeFragment();
//            fragmentTransaction.replace(R.id.constraint1,firstFragment);
//            fragmentTransaction.addToBackStack("fragmentStack");
//            fragmentTransaction.commit();
//
//            Bundle bundle = new Bundle();
//
//            firstFragment.setArguments(bundle);
//
//        });
//
//    }
//}
package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
//    private FirebaseAuth auth;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference df=database.getReference();
    EditText phoneno;
    EditText password;

    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // auth=FirebaseAuth.getInstance();
        phoneno=findViewById(R.id.editTextTextPersonName8);
        password=findViewById(R.id.editTextTextPersonName9);
        signIn=findViewById(R.id.button8);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonetxt = phoneno.getText().toString();
                String passwordtxt = password.getText().toString();
                if (phonetxt.isEmpty() || passwordtxt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "UserId and Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    df.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phonetxt)) {
                                final String getPassword = snapshot.child(phonetxt).child("password").getValue(String.class);
                                if (getPassword.equals(passwordtxt)) {
                                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                     startActivity(intent);
                                     Bundle bundle = new Bundle();
                                   signIn.setVisibility(View.GONE);
            if(getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().size()>0)
            {
                for(int i=0;i<getSupportFragmentManager().getFragments().size();i++)
                {
                    Fragment fragment = getSupportFragmentManager().getFragments().get(i);
                    if(fragment!=null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment firstFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.constraint1,firstFragment);
            fragmentTransaction.addToBackStack("fragmentStack");
            fragmentTransaction.commit();

//            Bundle bundle = new Bundle();

            firstFragment.setArguments(bundle);
                                    // Get shared preferences instance
                                    SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("uniqueId", phonetxt);
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    password.setError("Please enter correct Password");
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong UserId", Toast.LENGTH_SHORT).show();
                                phoneno.setError("Please enter correct LoginId");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });


//            @Override
//            public void onClick(View view) {
//                final String phonetxt=phoneno.getText().toString();
//                final String pass=password.getText().toString();
//                if(!.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches())
//                {
//                    if(!pass.isEmpty()){
//                        auth.signInWithEmailAndPassword(user,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, HomeFragment.class));
//                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(LoginActivity.this, "Login Failed.Try Again", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                    else {
//                        password.setError("Password Cannot be Empty");
//                    }
//                }
//                else if(user.isEmpty()){
//                    loginId.setError("Email Cannot be Empty");
//                }
//                else {
//                    loginId.setError("Please enter valid Credentials");
//                }
//
//            }


        TextView t1 = (TextView) findViewById(R.id.textView24);
        t1.setOnClickListener(view->{
            Intent intent = new Intent(this, RegisterActivity.class);

            startActivity(intent);
        });
    }
}