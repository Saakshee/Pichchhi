package com.example.sakshi.shree;

/**
 * Created by sakshi on 7/10/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakshi on 7/5/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<YoutubeVideo> youtubeVideoList;
    Context context;

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_video,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.videoWeb.loadData(youtubeVideoList.get(position).getVideoUrl(),"text/html","utf-8");
    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public VideoAdapter(Context context, List<YoutubeVideo> youtubeVideoList) {
        this.context = context;
        this.youtubeVideoList = new ArrayList<>();
        this.youtubeVideoList = youtubeVideoList;
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoWeb;


        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb= (WebView) itemView.findViewById(R.id.webVideoView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient());
        }
    }

}
