package com.example.sakshi.shree;

/**
 * Created by sakshi on 7/5/2017.
 */

public class YoutubeVideo {
    String videoUrl;

    public YoutubeVideo(){
    }

    public YoutubeVideo(String videoUrl){
        this.videoUrl=videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
