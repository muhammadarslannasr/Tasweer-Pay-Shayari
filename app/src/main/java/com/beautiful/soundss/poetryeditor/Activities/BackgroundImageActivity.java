package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.BackgroundRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesInternalRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Background;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class BackgroundImageActivity extends AppCompatActivity {

    private List<Background> backgroundList;
    private TextView text_countPhotos;
    private int ImageURI = 0;
    BackgroundRecyclerViewAdapter backgroundRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button selectPhotosBtn;
    private String image_path = null;
    private String poetry_text = null;
    private ImageView backlanguage;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_image);
        //loadIntertial();
        backgroundList = new ArrayList<>();
        backlanguage = findViewById(R.id.backlanguage);
        text_countPhotos = (TextView) findViewById(R.id.text_countPhotos);
        backgroundList.add(new Background(R.drawable.background_3,decodeImage(R.drawable.background_3)));
        backgroundList.add(new Background(R.drawable.background_6,decodeImage(R.drawable.background_6)));
        backgroundList.add(new Background(R.drawable.background_7,decodeImage(R.drawable.background_7)));
        backgroundList.add(new Background(R.drawable.background_8,decodeImage(R.drawable.background_8)));
        backgroundList.add(new Background(R.drawable.background_9,decodeImage(R.drawable.background_9)));
        backgroundList.add(new Background(R.drawable.background_10,decodeImage(R.drawable.background_10)));

        RecyclerView imagegrid = findViewById(R.id.backgroundRecyclerViewID);
        mLayoutManager = new GridLayoutManager(BackgroundImageActivity.this, 2);
        imagegrid.setLayoutManager(mLayoutManager);
        backgroundRecyclerViewAdapter = new BackgroundRecyclerViewAdapter(BackgroundImageActivity.this,backgroundList,BackgroundImageActivity.this);
        imagegrid.setAdapter(backgroundRecyclerViewAdapter);
        backgroundRecyclerViewAdapter.notifyDataSetChanged();

        selectPhotosBtn = (Button) findViewById(R.id.selectPhotosBtn);

        selectPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ImageURI > 0) {
                    Intent intent = getIntent();
                    intent.putExtra("image_path", ImageURI);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(BackgroundImageActivity.this, "Select at least one file!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                //showInterstial();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

        //showInterstial();
    }

    public Bitmap decodeImage(int image){
        return BitmapFactory.decodeResource(getResources(),image);
    }

    public void setValue(String value){
        text_countPhotos.setText(value);
    }

    public void ImageURI(int imageURI){

        this.ImageURI = imageURI;
    }

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(BackgroundImageActivity.this);
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
                mInterstitialAd = new InterstitialAd(BackgroundImageActivity.this);
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

        mInterstitialAd = new InterstitialAd(BackgroundImageActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }
}
