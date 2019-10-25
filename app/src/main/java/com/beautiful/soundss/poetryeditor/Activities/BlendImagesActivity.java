package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.Model.Model_images;
import com.beautiful.soundss.poetryeditor.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BlendImagesActivity extends AppCompatActivity {


    private TextView text_countPhotos;
    private ArrayList<Model_images> images;
    public  int countPhotos = 0;
    private Button selectPhotosBtn;
    private ArrayList<Model_images> photosArrayList   = new ArrayList<>();
    private ImageView backlanguage;
    private RecyclerView imagegrid;
    BlendImagesRecyclerViewAdapter blendImagesRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String ImageURI = null;
    private int  resultCode = 9162;
    Bitmap bitmap;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blend_images);
        text_countPhotos = (TextView) findViewById(R.id.text_countPhotos);
        backlanguage = findViewById(R.id.backlanguage);

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        images = getAllShownImagesPath(BlendImagesActivity.this);
        RecyclerView imagegrid = findViewById(R.id.PhoneImageGrid);
        mLayoutManager = new GridLayoutManager(BlendImagesActivity.this, 2);
        imagegrid.setLayoutManager(mLayoutManager);
        blendImagesRecyclerViewAdapter = new BlendImagesRecyclerViewAdapter(BlendImagesActivity.this, images,BlendImagesActivity.this);
        imagegrid.setAdapter(blendImagesRecyclerViewAdapter);
        blendImagesRecyclerViewAdapter.notifyDataSetChanged();

        selectPhotosBtn = (Button) findViewById(R.id.selectPhotosBtn);

        selectPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultCode == Crop.REQUEST_PICK){
                    if (ImageURI != null && !ImageURI.isEmpty()) {
                        Uri source_uri = Uri.fromFile(new File(ImageURI));
                        Uri destination_uri = Uri.fromFile(new File(getCacheDir(), "cropped"));
                        Crop.of(source_uri, destination_uri).asSquare().start(BlendImagesActivity.this);
                    }else {
                        Toast.makeText(BlendImagesActivity.this, "Select at least one image!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == Crop.REQUEST_PICK){
            }else if(requestCode == Crop.REQUEST_CROP){
                handle_crop(resultCode,data);
            }
        }
    }

    private void handle_crop(int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            //imageView.setImageURI(Crop.getOutput(data));
            try {
                //String path = Crop.getOutput(data).getPath();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Crop.getOutput(data));
                String returnPath = SaveImage(bitmap);
                String path = Environment.getExternalStorageDirectory() + "/saved_images/" + returnPath;
                File imgFile = new File(path);
                if (imgFile.exists()) {
                    Intent intent = new Intent(BlendImagesActivity.this, BlendActivity.class);
                    intent.putExtra("name", "imagePath_Crop");
                    intent.putExtra("image_path_crop", path);
                    intent.putExtra("image_path_crop_1", ImageURI);
                    startActivity(intent);
                    finish();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else if(resultCode == Crop.RESULT_ERROR){
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    private String SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Saved Image!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fname;
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
                MediaStore.Images.Media.BUCKET_ID};

        cursor = getContentResolver().query(uri, projection, null,
                null, null);

        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        }
        if (cursor != null) {
            column_index_folder_name = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
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

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(BlendImagesActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //Toast.makeText(AnimalsActivity.this, "Ad Loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                //Toast.makeText(AnimalsActivity.this, "Failed to Load!"+String.valueOf(errorCode), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                //Toast.makeText(Main3Activity.this, "Ad Opened!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd = new InterstitialAd(BlendImagesActivity.this);
                mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
                //mInterstitialAd.setAdUnitId("ca-app-pub-1081175552825381/4683358349");
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
                //Toast.makeText(Main3Activity.this, "Ad Closed!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showInterstial(){

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }

        mInterstitialAd = new InterstitialAd(BlendImagesActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
