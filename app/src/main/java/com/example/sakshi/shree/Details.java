package com.example.sakshi.shree;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
ImageView imageView;
    TextView txt;
    //Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();

        // Selected image id
        String position = i.getExtras().getString("images");
        imageView= (ImageView) findViewById(R.id.im);
        Glide.with(Details.this).load(position).into(imageView);
        String ps=i.getExtras().getString("names");
        txt=(TextView) findViewById(R.id.txt100);
        txt.setText(ps);
    }
    }

