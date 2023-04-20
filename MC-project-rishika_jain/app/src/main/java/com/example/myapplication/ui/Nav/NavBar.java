package com.example.myapplication.ui.Nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.activities.ProfileInfo;

public class NavBar extends AppCompatActivity {
    ImageView navBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        navBar1=findViewById(R.id.navBarProfileViewer);

        navBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavBar.this, ProfileInfo.class));
            }
        });

    }
}