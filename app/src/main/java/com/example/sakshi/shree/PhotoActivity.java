package com.example.sakshi.shree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.sakshi.shree.R.id.parent;
import static com.example.sakshi.shree.R.id.title;

public class PhotoActivity extends AppCompatActivity {
    public static final String DATA_URL = "http://pichchhi.yidemo.in/app/index.php?action=gallery";
//Button
    Button bsadhu;
    Button bteerth;
    Button bshashtra;
    //GridView Object
    private GridView gridView;
    ImageView bannerImg;
    TextView bannerTitle;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.header);

        //buttons
        bsadhu=(Button)findViewById(R.id.bsadhu);
        bshashtra=(Button)findViewById(R.id.bshastra);
        bteerth=(Button)findViewById(R.id.teerth);


        bshashtra.setBackgroundColor(Color.rgb(193,117,33));
        // Initialise ArrayLists
        images = new ArrayList<>();
        names = new ArrayList<>();

        bannerImg = (ImageView) findViewById(R.id.banner_image);
        bannerTitle = (TextView) findViewById(R.id.banner_title);
        gridView = (GridView) findViewById(R.id.gridView);
        getData();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), Details.class);
                // passing array index
                i.putExtra("images",images.get(position));
                i.putExtra("names",names.get(position));
                startActivity(i);
            }
        });
    }



    private void getData() {
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Fetching data...", false, false);

        //Creating a json array request to get the json from our api
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            bannerTitle.setText(jsonObject.getString("title") + "\n" + jsonObject.getString("tag_line"));
                            Glide.with(PhotoActivity.this).load(jsonObject.getString("banner")).into(bannerImg);
                            JSONArray jsonArray = jsonObject.getJSONArray("gallery");
                            loading.dismiss();
                            showGrid(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(PhotoActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showGrid(JSONArray jsonArray) {
        //Looping through all the elements of json array
        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString("cat_thumb"));
                names.add(obj.getString("cat_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, images, names);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }

    public void onSadhuClick(View arg0) {
        bsadhu.setBackgroundColor(Color.rgb(193,117,33));
        bsadhu.setPressed(true);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://shreephalnews.com/%E0%A4%B8%E0%A4%82%E0%A4%A4/"));
        startActivity(intent);
    }

    public void onTeerthClick(View view) {
        bteerth.setBackgroundColor(Color.rgb(193,117,33));
        bteerth.setPressed(true);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://shreephalnews.com/%E0%A4%A4%E0%A5%80%E0%A4%B0%E0%A5%8D%E0%A4%A5/"));
        startActivity(intent);
    }

    public void onShashtraClick(View view) {
        bshashtra.setBackgroundColor(Color.rgb(193,117,33));
        bshashtra.setPressed(true);
        startActivity(new Intent(PhotoActivity.this,PhotoActivity.class));
    }
}
