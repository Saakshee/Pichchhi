package com.example.sakshi.shree;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        findViewById(R.id.share_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.share_button) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/file/d/0B6Pm2McQdTQMbmJoc08xU0dkNm8/view?usp=drivesdk");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this App!");
            startActivity(Intent.createChooser(intent, "Share"));
        }
    }
}
