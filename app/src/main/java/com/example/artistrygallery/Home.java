package com.example.artistrygallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.artistrygallery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.art) {
                Intent intent = new Intent(getApplicationContext(), Art.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.welcome) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.search) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}
