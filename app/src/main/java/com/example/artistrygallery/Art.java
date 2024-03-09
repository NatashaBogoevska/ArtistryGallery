package com.example.artistrygallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Art extends AppCompatActivity {

    RecyclerView pRecyclerView, cRecyclerView;
    myAdapter pAdapter, cAdapter;

    String[] titles=null;
    String[] artist=null;
    int[]  year=null;
    TypedArray images=null;

    String[] titlesc=null;
    String[] artistc=null;
    int[] yearc=null;
    TypedArray imagesc=null;
    Button alarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        alarmBtn = findViewById(R.id.buttonAlarm);

        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SetAlarm.class);
                startActivity(intent);
                finish();
            }
        });

        pRecyclerView=(RecyclerView) findViewById(R.id.listProtein);
        pRecyclerView.setHasFixedSize(true);
        cRecyclerView=(RecyclerView) findViewById(R.id.listLowCalorie);
        cRecyclerView.setHasFixedSize(true);

        LinearLayoutManager pLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        pRecyclerView.setLayoutManager(pLayoutManager);
        cRecyclerView.setLayoutManager(cLayoutManager);

        pRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cRecyclerView.setItemAnimator(new DefaultItemAnimator());

        images=getResources().obtainTypedArray(R.array.images);
        titles=getResources().getStringArray(R.array.titles);
        artist=getResources().getStringArray(R.array.artist);
        year=getResources().getIntArray(R.array.year);

        imagesc=getResources().obtainTypedArray(R.array.imagesc);
        titlesc=getResources().getStringArray(R.array.titlesc);
        artistc=getResources().getStringArray(R.array.artistc);
        yearc=getResources().getIntArray(R.array.yearc);

        pAdapter=new myAdapter(images,titles,artist,year,R.layout.my_row,this);
        cAdapter=new myAdapter(imagesc,titlesc,artistc,yearc,R.layout.my_row,this);

        pRecyclerView.setAdapter(pAdapter);
        cRecyclerView.setAdapter(cAdapter);


    }
}