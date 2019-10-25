package com.beautiful.soundss.poetryeditor.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.beautiful.soundss.poetryeditor.R;

public class CropActivity extends AppCompatActivity {

    private ImageView imageView;
    private String crop_image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);


        imageView = findViewById(R.id.imageView1);
        //crop_image = getIntent().getStringExtra("image_path_crop");

        crop_image = getIntent().getExtras().getString("image_path_crop");
        if(crop_image!= null) {
            Uri imageUri=Uri.parse(crop_image);
            imageView.setImageURI(imageUri);
        }

        //ye nai h
    }
}
