package com.beautiful.soundss.poetryeditor.Model;

/**
 * Created by ArslanNasr on 10/12/2018.
 */

public class Creation {

    public String image_name;
    public String imagePath;

    public Creation(String image_name, String imagePath) {
        this.image_name = image_name;
        this.imagePath = imagePath;
    }

    public String getImage() {
        return image_name;
    }

    public void setImage(String image) {
        this.image_name = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
