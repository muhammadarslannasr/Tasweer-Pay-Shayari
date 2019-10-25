package com.beautiful.soundss.poetryeditor.Model;

import android.graphics.Bitmap;

/**
 * Created by ArslanNasr on 10/9/2018.
 */

public class Background {

    public int image;
    public Bitmap bitmap;

    public Background(int image, Bitmap bitmap) {
        this.image = image;
        this.bitmap = bitmap;
    }

    public Background(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
