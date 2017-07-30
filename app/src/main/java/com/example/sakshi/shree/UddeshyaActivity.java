package com.example.sakshi.shree;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class UddeshyaActivity extends AppCompatActivity {
TextView textView;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uddeshya);
        textView=(TextView)findViewById(R.id.text1);
        scrollView=(ScrollView) findViewById(R.id.activity_uddeshya);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);


    }
}
