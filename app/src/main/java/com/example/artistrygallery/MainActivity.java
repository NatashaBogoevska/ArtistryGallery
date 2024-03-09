package com.example.artistrygallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailL,passL;
    Button buttL;
    TextView regL;
    FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), Welcome.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth=FirebaseAuth.getInstance();
        emailL=(EditText) findViewById(R.id.email);
        passL=(EditText) findViewById(R.id.password);
        buttL=(Button) findViewById(R.id.Login);
        regL=(TextView) findViewById(R.id.registerLogin);



        regL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(intent);
                finish();
            }
        });

        buttL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String emaillog,passlog;
                emaillog=String.valueOf(emailL.getText());
                passlog=String.valueOf(passL.getText());

                if(TextUtils.isEmpty(emaillog)){
                    Toast.makeText(MainActivity.this,"Enter an email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(passlog)){
                    Toast.makeText(MainActivity.this,"Enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emaillog, passlog)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Login succesfull",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(), Welcome.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }
}