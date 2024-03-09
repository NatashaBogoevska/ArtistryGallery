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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUser extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText fname, lname, birth, age, editTextEmail, editTextPassword;
    Button buttonReg;

    TextView textView;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PRED_NAME = "mypref";
    private static final String KEY_NAME = "Name";
    private static final String KEY_EMAIL = "Email";


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
        setContentView(R.layout.activity_register_user);

        mAuth= FirebaseAuth.getInstance();
        fname=(EditText) findViewById(R.id.firstName);
        lname=(EditText) findViewById(R.id.lastName);
        birth=(EditText) findViewById(R.id.dateBirth);
        age=(EditText) findViewById(R.id.years);
        editTextEmail=(EditText) findViewById(R.id.emailRegister);
        editTextPassword=(EditText) findViewById(R.id.passwordRegister);
        buttonReg=(Button) findViewById(R.id.buttonRegister);
        textView=findViewById(R.id.registerNow);

        sharedPreferences =getSharedPreferences(SHARED_PRED_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name!=null){
            Intent intent = new Intent(RegisterUser.this, Welcome.class);
            startActivity(intent);
        }

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(intent);
                finish();//finish the current activity
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, fname.getText().toString());
                editor.putString(KEY_EMAIL, editTextEmail.getText().toString());
                editor.apply();
                String name,surname,dateB,email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                int years;
                name=String.valueOf(fname.getText());
                surname=String.valueOf(lname.getText());
                dateB=String.valueOf(birth.getText());
                years=Integer.parseInt(age.getText().toString());


                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterUser.this,"Enter a first name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(surname)){
                    Toast.makeText(RegisterUser.this,"Enter a last name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(dateB)){
                    Toast.makeText(RegisterUser.this,"Enter a date of birth",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterUser.this,"Enter an email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterUser.this,"Enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(years<=0){
                    Toast.makeText(RegisterUser.this,"Enter your age",Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(RegisterUser.this,"Account created",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(RegisterUser.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}