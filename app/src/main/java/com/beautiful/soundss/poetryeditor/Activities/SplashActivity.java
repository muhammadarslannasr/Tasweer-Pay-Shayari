package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.beautiful.soundss.poetryeditor.Model.PoetrySingleton;
import com.beautiful.soundss.poetryeditor.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView splashTextViewID;
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Random random = new Random();
        Poetry p = PoetrySingleton.getInstance().getSplashPoetryList().get(random.nextInt(PoetrySingleton.getInstance().getSplashPoetryList().size()));
        //Casting Widget's
        imageView = findViewById(R.id.imageView);
        splashTextViewID = findViewById(R.id.splashTextViewID);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Jameel Noori Nastaleeq Kasheeda.ttf");
        splashTextViewID.setText(p.getPoetry());
        splashTextViewID.setTypeface(typeface);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                supportFinishAfterTransition();
                // close this activity
                finish();
                //loadAd();
            }
        }, SPLASH_TIME_OUT);
    }
}
