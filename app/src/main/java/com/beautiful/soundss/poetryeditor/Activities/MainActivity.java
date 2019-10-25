package com.beautiful.soundss.poetryeditor.Activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button editorID,creationID,facebookID;
    private ImageButton rateUsID,shareID;
    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.
    String[] permissions;
    String url = null;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editorID = findViewById(R.id.editorID);
        creationID = findViewById(R.id.creationID);
        facebookID = findViewById(R.id.facebookID);
        rateUsID = findViewById(R.id.rateUsID);
        shareID = findViewById(R.id.shareID);
        permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkPermissions()){
            //  permissions  granted.
        }

        rateUsID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }
        });

        shareID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp(MainActivity.this);
            }
        });

        //Editor Button onClickListener
        editorID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,PoetryPhotoEditorActivity.class));
            }
        });

        creationID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,CreationActivity.class));
            }
        });

        facebookID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url = "https://www.facebook.com/TheXsolutions/";

                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(MainActivity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }

    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, R.string.app_name + ". Check out App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    private void showInterstial(){

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }

        mInterstitialAd = new InterstitialAd(MainActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }

    void loadIntertial(){

        mInterstitialAd = new InterstitialAd(MainActivity.this);
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
                mInterstitialAd = new InterstitialAd(MainActivity.this);
                mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
                //mInterstitialAd.setAdUnitId("ca-app-pub-1081175552825381/4683358349");
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
                //Toast.makeText(Main3Activity.this, "Ad Closed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
