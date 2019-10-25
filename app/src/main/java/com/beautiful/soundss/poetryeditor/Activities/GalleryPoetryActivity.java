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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesInternalRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesPoetryGalleryRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.Model.Model_images;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class GalleryPoetryActivity extends AppCompatActivity {

    private TextView text_countPhotos;
    private ArrayList<Model_images> images;
    public  int countPhotos = 0;
    private Button selectPhotosBtn;
    private ArrayList<Model_images> photosArrayList   = new ArrayList<>();
    private ImageView backlanguage;
    private RecyclerView imagegrid;
    BlendImagesPoetryGalleryRecyclerViewAdapter blendImagesPoetryGalleryRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String ImageURI = null;
    private String image_path = null;
    private String poetry_text = null;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_poetry);
        text_countPhotos = (TextView) findViewById(R.id.text_countPhotos);
        backlanguage = findViewById(R.id.backlanguage);

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        images = getAllShownImagesPath(GalleryPoetryActivity.this);
        RecyclerView imagegrid = findViewById(R.id.PhoneImageGrid);
        mLayoutManager = new GridLayoutManager(GalleryPoetryActivity.this, 2);
        imagegrid.setLayoutManager(mLayoutManager);
        blendImagesPoetryGalleryRecyclerViewAdapter = new BlendImagesPoetryGalleryRecyclerViewAdapter(GalleryPoetryActivity.this, images,GalleryPoetryActivity.this);
        imagegrid.setAdapter(blendImagesPoetryGalleryRecyclerViewAdapter);
        blendImagesPoetryGalleryRecyclerViewAdapter.notifyDataSetChanged();

        selectPhotosBtn = (Button) findViewById(R.id.selectPhotosBtn);

        selectPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ImageURI != null && !ImageURI.isEmpty()) {
                    Intent intent = new Intent(getIntent());
                    intent.putExtra("image_path", ImageURI);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(GalleryPoetryActivity.this, "Select at least one file!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(GalleryPoetryActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_lenovo_ID).addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());

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
                mInterstitialAd = new InterstitialAd(GalleryPoetryActivity.this);
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

        mInterstitialAd = new InterstitialAd(GalleryPoetryActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }
}
