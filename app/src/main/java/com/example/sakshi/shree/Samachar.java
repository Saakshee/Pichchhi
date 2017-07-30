package com.example.sakshi.shree;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Samachar extends AppCompatActivity {

    TextView headingText, descText, urlText;
    ImageView imageView;

    List<News> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samachar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);

        headingText = (TextView) findViewById(R.id.headText);
        descText = (TextView) findViewById(R.id.descText);
        urlText = (TextView) findViewById(R.id.urlText);
        imageView = (ImageView) findViewById(R.id.imgView);
        requestNews("http://pichchhi.yidemo.in/app/index.php?action=news");
    }

    public void requestNews(String uri) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length() > 0) {
                            dataList = NewsJsonParser.parsefeed(response);
                            headingText.setText(dataList.get(0).getHeading());
                            descText.setText(dataList.get(0).getDescription());
                            urlText.setText(dataList.get(0).getUrl());
                            Glide.with(Samachar.this).load(dataList.get(0).getBanner()).into(imageView);
                        } else {

                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add("No items Found");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

    }
}

