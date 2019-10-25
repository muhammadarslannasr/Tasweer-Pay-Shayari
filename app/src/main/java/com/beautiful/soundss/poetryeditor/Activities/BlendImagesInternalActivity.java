package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesInternalRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Model_images;
import com.beautiful.soundss.poetryeditor.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BlendImagesInternalActivity extends AppCompatActivity {


    private TextView text_countPhotos;
    private ArrayList<Model_images> images;
    public  int countPhotos = 0;
    private Button selectPhotosBtn;
    private ArrayList<Model_images> photosArrayList   = new ArrayList<>();
    private ImageView backlanguage;
    private RecyclerView imagegrid;
    BlendImagesInternalRecyclerViewAdapter blendImagesRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String ImageURI = null;
    private String image_path = null;
    private String poetry_text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blend_images_internal);

        text_countPhotos = (TextView) findViewById(R.id.text_countPhotos);
        backlanguage = findViewById(R.id.backlanguage);
            //image_path = getIntent().getStringExtra("image_crop_path");

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


//        GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
//
//        imagegrid.setAdapter(new ImageAdapter(BlendImagesActivity.this));
        images = getAllShownImagesPath(BlendImagesInternalActivity.this);
        RecyclerView imagegrid = findViewById(R.id.PhoneImageGrid);
        mLayoutManager = new GridLayoutManager(BlendImagesInternalActivity.this, 2);
        imagegrid.setLayoutManager(mLayoutManager);
        blendImagesRecyclerViewAdapter = new BlendImagesInternalRecyclerViewAdapter(BlendImagesInternalActivity.this, images,BlendImagesInternalActivity.this);
        imagegrid.setAdapter(blendImagesRecyclerViewAdapter);
        blendImagesRecyclerViewAdapter.notifyDataSetChanged();

        selectPhotosBtn = (Button) findViewById(R.id.selectPhotosBtn);

        selectPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ImageURI != null && !ImageURI.isEmpty()) {
                    Intent intent = getIntent();
                    intent.putExtra("image_path", ImageURI);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(BlendImagesInternalActivity.this, "Select at least one file!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void setValue(String value){
        text_countPhotos.setText(value);
    }

    public void ImageURI(String imageURI){

        this.ImageURI = imageURI;
    }


    public ArrayList<Model_images> getAllShownImagesPath(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data = 0, column_index_folder_name = 0;
        ArrayList<Model_images> listOfAllImages = new ArrayList<Model_images>();
        String absolutePathOfImage = null;
        String display_name = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = getContentResolver().query(uri, projection, null,
                null, null);

        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        }
        if (cursor != null) {
            column_index_folder_name = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                display_name = cursor.getString(column_index_folder_name);
                listOfAllImages.add(new Model_images(absolutePathOfImage,display_name));
            }
        }
        return listOfAllImages;
    }
}
