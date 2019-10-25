package com.beautiful.soundss.poetryeditor.Activities;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesInternalRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.CreationPicturesRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Creation;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreationActivity extends AppCompatActivity {

    private ImageView backlanguage;
    private RecyclerView creationRecyclerViewID;
    private List<Creation> creationList;
    private RecyclerView.LayoutManager mLayoutManager;
    private CreationPicturesRecyclerViewAdapter creationPicturesRecyclerViewAdapter;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        backlanguage = findViewById(R.id.backlanguage);
        creationRecyclerViewID = findViewById(R.id.creationRecyclerViewID);
        creationList = new ArrayList<>();
        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLayoutManager = new GridLayoutManager(CreationActivity.this, 2);
        creationRecyclerViewID.setLayoutManager(mLayoutManager);
        creationPicturesRecyclerViewAdapter = new CreationPicturesRecyclerViewAdapter(CreationActivity.this,listAllFiles());
        creationRecyclerViewID.setAdapter(creationPicturesRecyclerViewAdapter);
        creationPicturesRecyclerViewAdapter.notifyDataSetChanged();


    }

    private List<Creation> listAllFiles(){
        String path = Environment.getExternalStorageDirectory().toString()+"/saved_images";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++) {
            Log.d("Files", "FileName:" + files[i].getAbsolutePath());
            String image_path = files[i].getAbsolutePath();
            String image_name = files[i].getName();
            creationList.add(new Creation(image_name,image_path));
        }

        return creationList;
        }

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(CreationActivity.this);
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
                mInterstitialAd = new InterstitialAd(CreationActivity.this);
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

        mInterstitialAd = new InterstitialAd(CreationActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    }
