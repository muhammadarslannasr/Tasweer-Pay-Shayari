package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class CustomKeyboardActivity extends AppCompatActivity {

    private ImageView backlanguage;
    private Button closeBtnID,sendbuttonId;
    private EditText large_textId;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_keyboard);
        backlanguage = findViewById(R.id.backlanguage);
        closeBtnID = findViewById(R.id.closeBtnID);
        sendbuttonId = findViewById(R.id.sendbuttonId);
        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        large_textId = findViewById(R.id.large_textId);
        large_textId.setGravity(Gravity.CENTER);
        large_textId.setGravity(View.FOCUS_RIGHT);
        large_textId.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(large_textId, 0);
        }


        sendbuttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!large_textId.getText().toString().trim().isEmpty()){
                    Intent intent = new Intent(CustomKeyboardActivity.this,PoetryBlendActivity.class);
                    intent.putExtra("name","custom_keyboard_text");
                    intent.putExtra("poetry_text",large_textId.getText().toString().trim());
                    startActivity(intent);
                }else {
                    Toast.makeText(CustomKeyboardActivity.this, "Message Box cannot be Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        closeBtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(CustomKeyboardActivity.this);
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
                mInterstitialAd = new InterstitialAd(CustomKeyboardActivity.this);
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

        mInterstitialAd = new InterstitialAd(CustomKeyboardActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }
}
