package com.example.sakshi.shree;

import android.graphics.Bitmap;

/**
 * Created by sakshi on 7/21/2017.
 */
public class ImageItem {
    private Bitmap image;


    public ImageItem(Bitmap image) {
        super();
        this.image = image;

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


}
