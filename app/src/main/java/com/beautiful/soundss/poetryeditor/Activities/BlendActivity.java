package com.beautiful.soundss.poetryeditor.Activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.net.LinkAddress;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.BlendImagesRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Adapters.PoetryRecyclerViewAdapter;
import com.beautiful.soundss.poetryeditor.Model.Credentials;
import com.beautiful.soundss.poetryeditor.Model.FileUtils;
import com.beautiful.soundss.poetryeditor.R;
import com.beautiful.soundss.poetryeditor.StrickerImageView.BubbleInputDialog;
import com.beautiful.soundss.poetryeditor.StrickerImageView.BubbleTextView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerImageView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerTextView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerViewNewLibraryChina;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class BlendActivity extends AppCompatActivity {
    //1st Activity
    public static final String ACTION = "action";
    public static final String ACTION_IMAGE = "action_image";
    public static final String ACTION_TEXT = "action_text";

    private Button galleyID, backgroundID, imageBrightnessID, checkedID;
    private ImageView backgroundImageID, selectedImageID, downloadbtnID;
    private String imagePath;
    private String path_background = null;
    private int background_customPath = 0;
    private String path_Crop = null;
    private String poetry_text = null;
    private BubbleInputDialog mBubbleInputDialog;
    private StickerViewNewLibraryChina mCurrentView;
    private LinearLayout bottomViewID, anotherBottomViewID;
    private CoordinatorLayout mainLayoutID;
    private BubbleTextView mCurrentEditTextView;
    private ArrayList<View> mViews;
    String path = null;
    private RelativeLayout mContentRootView;

    private View mAddSticker;
    StickerViewNewLibraryChina stickerView;
    private String mAction;

    private View mAddBubble;
    private RelativeLayout blendActivtyID;
    Bitmap convertesBitmap;
    SharedPreferences prefs;
    private String image_path;
    private String poetry_text_internal = null;
    SeekBar blurBar;
    int blurValue = 1;
    Bitmap bitmap_Source;
    public static final int REQUEST_CODE_ONE = 1;
    public static final int REQUEST_CODE_TWO = 2;
    private ImageView backlanguage;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blend);
        mContentRootView = (RelativeLayout) findViewById(R.id.rl_content_root);
        backgroundImageID = findViewById(R.id.backgroundImageID);
        blendActivtyID = findViewById(R.id.blendActivtyID);
        blurBar = findViewById(R.id.blurBar);
        anotherBottomViewID = findViewById(R.id.anotherBottomViewID);
        mViews = new ArrayList<>();
        galleyID = findViewById(R.id.galleyID);
        backgroundID = findViewById(R.id.backgroundID);
        imageBrightnessID = findViewById(R.id.imageBrightnessID);
        downloadbtnID = findViewById(R.id.downloadbtnID);
        bottomViewID = findViewById(R.id.bottomViewID);
        checkedID = findViewById(R.id.checkedID);
        backlanguage = findViewById(R.id.backlanguage);
        blurBar.setOnSeekBarChangeListener(blurBarChangeListener);
        if (getIntent().getStringExtra("name").compareTo("text") == 0) {
            poetry_text = getIntent().getStringExtra("poetry_text");
            if (poetry_text != null && !poetry_text.isEmpty()) {
                addBubble(poetry_text);
            }
            blendActivtyID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentEditTextView.setInEdit(false);

                }
            });
        } else if (getIntent().getStringExtra("name").compareTo("imagePath_Crop") == 0) {
            imagePath = getIntent().getStringExtra("image_path_crop");
            path_Crop = getIntent().getStringExtra("image_path_crop_1");
            if (imagePath != null && !imagePath.isEmpty()) {
                setmAddSticker();
                bitmap_Source = decodeFile(imagePath);
            }
            blendActivtyID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mCurrentView.setInEdit(false);

                }
            });

        }

        galleyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlendActivity.this, BlendImagesInternalActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ONE);
            }
        });

        backgroundID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlendActivity.this, BackgroundImageActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TWO);
            }
        });

        downloadbtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomViewID.setVisibility(View.GONE);
                if (mCurrentView != null || mCurrentEditTextView != null) {
                    if (mCurrentView != null) {
                        mCurrentView.setInEdit(false);
                    }
                    if (mCurrentEditTextView != null) {
                        mCurrentEditTextView.setInEdit(false);
                    }
                }

                generateBitmap();
            }
        });

        imageBrightnessID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomViewID.setVisibility(View.GONE);
                anotherBottomViewID.setVisibility(View.VISIBLE);
            }
        });

        checkedID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomViewID.setVisibility(View.VISIBLE);
                anotherBottomViewID.setVisibility(View.GONE);
            }
        });

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    SeekBar.OnSeekBarChangeListener blurBarChangeListener
            = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            blurValue = progress + 1;
//                mViews.remove(stickerView);
            //  stickerView.setImageBitmap(CropBitmapTransparency(bitmap_Source));
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageURI(Uri.parse(imagePath));

//            AlphaAnimation alpha = new AlphaAnimation(0.5F, 0.5F);
//            alpha.setDuration(0);
//            alpha.setFillAfter(true);
//            imageView.startAnimation(alpha);
            imageView.setAlpha(0.5f);
            imageView.buildDrawingCache();
            stickerView.setImageBitmap(imageView.getDrawingCache());
            //Toast.makeText(BlendActivity.this, "5", Toast.LENGTH_SHORT).show();
//                mContentRootView.removeView(stickerView);
            //setmAddStickerBitmap(CropBitmapTransparency(bitmap_Source));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_ONE && resultCode == RESULT_OK) {
                String requiredValue = data.getStringExtra("image_path");
                backgroundImageID.setImageURI(Uri.parse(requiredValue));
            } else if (requestCode == REQUEST_CODE_TWO && resultCode == RESULT_OK) {
                int requiredValue = data.getIntExtra("image_path", 0);
                backgroundImageID.setImageResource(requiredValue);
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }


    Bitmap CropBitmapTransparency(Bitmap sourceBitmap) {
        int minX = sourceBitmap.getWidth();
        int minY = sourceBitmap.getHeight();
        int maxX = -1;
        int maxY = -1;
        for (int y = 0; y < sourceBitmap.getHeight(); y++) {
            for (int x = 0; x < sourceBitmap.getWidth(); x++) {
                int alpha = (sourceBitmap.getPixel(x, y) >> 24) & 255;
                if (alpha > 0)   // pixel is not 100% transparent
                {
                    if (x < minX)
                        minX = x;
                    if (x > maxX)
                        maxX = x;
                    if (y < minY)
                        minY = y;
                    if (y > maxY)
                        maxY = y;
                }
            }
        }
        if ((maxX < minX) || (maxY < minY))
            return null; // Bitmap is entirely transparent

        // crop bitmap to non-transparent area and return:
        return Bitmap.createBitmap(sourceBitmap, minX, minY, (maxX - minX) + 1, (maxY - minY) + 1);
    }

    private Bitmap processingBitmap_Blur(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BlurMaskFilter blurMaskFilter;
        Paint paintBlur = new Paint();

        Bitmap dest = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(dest);

        //Create background in White
        Bitmap alpha = src.extractAlpha();
        paintBlur.setColor(0xFFFFFFFF);
        canvas.drawBitmap(alpha, 0, 0, paintBlur);

        //Create outer blur, in White
        blurMaskFilter = new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.OUTER);
        paintBlur.setMaskFilter(blurMaskFilter);
        canvas.drawBitmap(alpha, 0, 0, paintBlur);

        //Create inner blur
        blurMaskFilter = new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.INNER);
        paintBlur.setMaskFilter(blurMaskFilter);
        canvas.drawBitmap(src, 0, 0, paintBlur);
        dest.recycle();
        return dest;
    }

    public static Bitmap decodeFile(String photoPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);

        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inPreferQualityOverSpeed = true;

        return BitmapFactory.decodeFile(photoPath, options);
    }

    private void setmAddStickerPrefrences(String path) {
        stickerView = new StickerViewNewLibraryChina(this);
        //stickerView.setImageResource(R.mipmap.ic_cat);
//        prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
//        String path = prefs.getString("imagePath",null); // 0 is default
        stickerView.setImageUri(path);
        stickerView.setOperationListener(new StickerViewNewLibraryChina.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerViewNewLibraryChina stickerView) {
                if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerViewNewLibraryChina stickerView) {
                int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerViewNewLibraryChina stickerTemp = (StickerViewNewLibraryChina) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mViews.add(stickerView);
        setCurrentEdit(stickerView);

    }


    private void setmAddSticker() {
        stickerView = new StickerViewNewLibraryChina(this);
        //stickerView.setImageResource(R.mipmap.ic_cat);
        stickerView.setImageUri(imagePath);
        stickerView.setOperationListener(new StickerViewNewLibraryChina.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerViewNewLibraryChina stickerView) {
                if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerViewNewLibraryChina stickerView) {
                int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerViewNewLibraryChina stickerTemp = (StickerViewNewLibraryChina) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mViews.add(stickerView);
        setCurrentEdit(stickerView);

    }

    private void setmAddStickerBitmap(Bitmap bitmap_Source) {
        stickerView = new StickerViewNewLibraryChina(this);
        //stickerView.setImageResource(R.mipmap.ic_cat);
        stickerView.setImageBitmap(bitmap_Source);
        stickerView.setOperationListener(new StickerViewNewLibraryChina.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerViewNewLibraryChina stickerView) {
                if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerViewNewLibraryChina stickerView) {
                int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerViewNewLibraryChina stickerTemp = (StickerViewNewLibraryChina) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mViews.add(stickerView);
        setCurrentEdit(stickerView);

    }

    private void addBubble(String poetry_text) {
        final BubbleTextView bubbleTextView = new BubbleTextView(this,
                Color.WHITE, 0, poetry_text);
        bubbleTextView.setImageResource(R.mipmap.bubble_7_rb);
        bubbleTextView.setOperationListener(new BubbleTextView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(bubbleTextView);
                mContentRootView.removeView(bubbleTextView);
            }

            @Override
            public void onEdit(BubbleTextView bubbleTextView) {
                if (mCurrentView != null) {
                    mCurrentView.setInEdit(false);
                }
                mCurrentEditTextView.setInEdit(false);
                mCurrentEditTextView = bubbleTextView;
                mCurrentEditTextView.setInEdit(true);
            }

            @Override
            public void onClick(BubbleTextView bubbleTextView) {
                mBubbleInputDialog.setBubbleTextView(bubbleTextView);
                mBubbleInputDialog.show();
            }

            @Override
            public void onTop(BubbleTextView bubbleTextView) {
                int position = mViews.indexOf(bubbleTextView);
                if (position == mViews.size() - 1) {
                    return;
                }
                BubbleTextView textView = (BubbleTextView) mViews.remove(position);
                mViews.add(mViews.size(), textView);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(bubbleTextView, lp);
        mViews.add(bubbleTextView);
        setCurrentEdit(bubbleTextView);
    }

    private void addBubblePrefrences(final String poetry_text) {
        final BubbleTextView bubbleTextView = new BubbleTextView(this,
                Color.WHITE, 0, poetry_text);
        bubbleTextView.setImageResource(R.mipmap.bubble_7_rb);
        bubbleTextView.setOperationListener(new BubbleTextView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(bubbleTextView);
                mContentRootView.removeView(bubbleTextView);
            }

            @Override
            public void onEdit(BubbleTextView bubbleTextView) {
                if (mCurrentView != null) {
                    mCurrentView.setInEdit(false);
                }
                mCurrentEditTextView.setInEdit(false);
                mCurrentEditTextView = bubbleTextView;
                mCurrentEditTextView.setInEdit(true);
            }

            @Override
            public void onClick(BubbleTextView bubbleTextView) {
                mBubbleInputDialog.setBubbleTextView(bubbleTextView);
                mBubbleInputDialog.show();
            }

            @Override
            public void onTop(BubbleTextView bubbleTextView) {
                int position = mViews.indexOf(bubbleTextView);
                if (position == mViews.size() - 1) {
                    return;
                }
                BubbleTextView textView = (BubbleTextView) mViews.remove(position);
                mViews.add(mViews.size(), textView);
            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(bubbleTextView, lp);
        mViews.add(bubbleTextView);
        setCurrentEdit(bubbleTextView);
    }


    private void setCurrentEdit(StickerViewNewLibraryChina stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }

    private void setCurrentEdit(BubbleTextView bubbleTextView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentEditTextView = bubbleTextView;
        mCurrentEditTextView.setInEdit(true);
    }

    private void generateBitmap() {

        Bitmap bitmap = Bitmap.createBitmap(mContentRootView.getWidth(),
                mContentRootView.getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mContentRootView.draw(canvas);
        String returnPath = SaveImage(bitmap);
        String path = Environment.getExternalStorageDirectory() + "/saved_images/" + returnPath;
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        File imgFile = new File(path);
        if (imgFile.exists()) {
            //Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DownloadShareActivity.class);
            intent.putExtra("image", path);
            startActivity(intent);
            finish();
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
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    void loadIntertial() {

        mInterstitialAd = new InterstitialAd(BlendActivity.this);
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
                mInterstitialAd = new InterstitialAd(BlendActivity.this);
                mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
                //mInterstitialAd.setAdUnitId("ca-app-pub-1081175552825381/4683358349");
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
                //Toast.makeText(Main3Activity.this, "Ad Closed!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showInterstial() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

        mInterstitialAd = new InterstitialAd(BlendActivity.this);
        mInterstitialAd.setAdUnitId(Credentials.test_interstial_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(Credentials.device_huwawi_P10Lite_ID).build());
    }
}
