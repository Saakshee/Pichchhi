package com.example.sakshi.shree;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class VideoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideos = new Vector<YoutubeVideo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NU5c5QrLnc4\" frameborder=\"0\" allowfullscreen></iframe>" ));
        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/o_60RhZjyrY\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideo(" <iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/r_vnW1NCGok\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oTzbNIwk6Hc\" frameborder=\"0\" allowfullscreen></iframe>"));
        VideoAdapter videoAdapter = new VideoAdapter(VideoActivity.this, youtubeVideos);
        recyclerView.setAdapter(videoAdapter);

    }
}


