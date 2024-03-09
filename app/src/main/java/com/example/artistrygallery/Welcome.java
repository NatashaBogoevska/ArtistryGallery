package com.example.artistrygallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {

    FirebaseAuth auth;
    Button logO, alarmBtn;
    FirebaseUser user;

    TextView tvName, tvEmail;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PRED_NAME = "mypref";
    private static final String KEY_NAME = "Name";
    private static final String KEY_EMAIL = "Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth=FirebaseAuth.getInstance();
        logO=(Button) findViewById(R.id.logOut);
        user=auth.getCurrentUser();//this will get the current user
        tvName= findViewById(R.id.textView3);
        tvEmail= findViewById(R.id.textView4);
        alarmBtn = findViewById(R.id.buttonAlarm);

        sharedPreferences=getSharedPreferences(SHARED_PRED_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);


        if(name != null && email != null){
            tvName.setText(name);
            tvEmail.setText(email);
        }

        if(user==null){//if the user is null we will finish this activity and open the login activity
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.art) {
                        startActivity(new Intent(Welcome.this, Art.class));
                        return true;
                    } else if (id == R.id.welcome) {
                        startActivity(new Intent(Welcome.this, Welcome.class));
                        return true;
                    } else if (id == R.id.search) {
                        startActivity(new Intent(Welcome.this, Search.class));
                        return true;
                    }

                    return false;
                }

            });
        }

        logO.setOnClickListener(new View.OnClickListener(){//here we can sign out the user
            @Override
            public void onClick(View view){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                FirebaseAuth.getInstance().signOut();
                //we close the current activity and open the login activity
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}