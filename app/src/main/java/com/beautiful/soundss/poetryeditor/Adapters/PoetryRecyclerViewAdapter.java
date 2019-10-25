package com.beautiful.soundss.poetryeditor.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautiful.soundss.poetryeditor.Activities.BlendActivity;
import com.beautiful.soundss.poetryeditor.Activities.PoetryBlendActivity;
import com.beautiful.soundss.poetryeditor.Activities.PoetryPhotoEditorActivity;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.R;
import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;
public class PoetryRecyclerViewAdapter extends RecyclerView.Adapter<PoetryRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Poetry> poetryList;
    private boolean showingFirst = true;
    private String poetry_text_current = null;
    private InterstitialAd mInterstitialAd;

    public PoetryRecyclerViewAdapter(final Context context, List<Poetry> poetryList) {
        this.context = context;
        this.poetryList = poetryList;
    }

    @NonNull
    @Override
    public PoetryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poetry_row,parent,false);
        return new PoetryRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull PoetryRecyclerViewAdapter.ViewHolder holder, int position) {

        Poetry poetry = poetryList.get(position);
        holder.poetry_textID.setText(poetry.getPoetry());

    }

    @Override
    public int getItemCount() {
        return poetryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView poetry_textID;

        public ViewHolder(final View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            poetry_textID = itemView.findViewById(R.id.poetry_textID);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Jameel Noori Nastaleeq Kasheeda.ttf");
            poetry_textID.setTypeface(typeface);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Poetry poetry = poetryList.get(pos);
                    Intent intent = new Intent(context, PoetryBlendActivity.class);
                    intent.putExtra("name","text");
                    intent.putExtra("poetry_text",poetry.getPoetry());
                    //intent.putExtra("poetry_text_current",poetry_text_current);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });

        }
    }

   private void loadIntertial(){

        mInterstitialAd = new InterstitialAd(context);
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
                mInterstitialAd = new InterstitialAd(context);
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

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }
}
