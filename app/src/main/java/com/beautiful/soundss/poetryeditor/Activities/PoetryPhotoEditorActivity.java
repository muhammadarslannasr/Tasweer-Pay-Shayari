package com.beautiful.soundss.poetryeditor.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class PoetryPhotoEditorActivity extends AppCompatActivity {

    private Button choosePoetryID,blendImagesID,customTextID,urduKeyBoardLinkID;
    private ImageView backlanguage;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry_photo_editor);
        choosePoetryID = findViewById(R.id.choosePoetryID);
        choosePoetryID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PoetryPhotoEditorActivity.this,ChoosePoetryActivity.class));
            }
        });

        blendImagesID = findViewById(R.id.blendImagesID);
        blendImagesID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PoetryPhotoEditorActivity.this,BlendImagesActivity.class));
            }
        });

        customTextID = findViewById(R.id.customTextID);
        customTextID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PoetryPhotoEditorActivity.this,CustomKeyboardActivity.class));
            }
        });

        backlanguage = findViewById(R.id.backlanguage);
        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        urduKeyBoardLinkID = findViewById(R.id.urduKeyBoardLinkID);
        urduKeyBoardLinkID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.pakdata.easyurdu")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.pakdata.easyurdu")));
                }
            }
        });
    }

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(PoetryPhotoEditorActivity.this);
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
                mInterstitialAd = new InterstitialAd(PoetryPhotoEditorActivity.this);
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

        mInterstitialAd = new InterstitialAd(PoetryPhotoEditorActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
