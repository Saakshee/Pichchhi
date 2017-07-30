package com.example.sakshi.shree;

/**
 * Created by sakshi on 7/16/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by sakshi on 7/10/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    //Imageloader to load images
    private ImageLoader imageLoader;

    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> images;
    private ArrayList<String> names;

    public GridViewAdapter(Context context, ArrayList<String> images, ArrayList<String> names) {
        //Getting all the values
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_image);
        TextView textView = (TextView) view.findViewById(R.id.grid_item_title);
        //Initializing ImageLoader
        Glide.with(context).load(images.get(position)).into(imageView);
        //Creating a textview to show the title
        textView.setText(names.get(position));

        //Returnint the layout
        return view;
    }
}