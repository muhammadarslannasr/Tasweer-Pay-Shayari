package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Adapters.HorizontalAdapter;
import com.beautiful.soundss.poetryeditor.Model.Colors;
import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.beautiful.soundss.poetryeditor.R;
import com.beautiful.soundss.poetryeditor.StrickerImageView.BubbleInputDialog;
import com.beautiful.soundss.poetryeditor.StrickerImageView.BubbleTextView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerTextView;
import com.beautiful.soundss.poetryeditor.StrickerImageView.StickerViewNewLibraryChina;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoetryBlendActivity extends AppCompatActivity {

    public static final String ACTION = "action";
    public static final String ACTION_IMAGE = "action_image";
    public static final String ACTION_TEXT = "action_text";

    private Button galleyID, backgroundID,checkedID;
    private ImageView selectedImageID, downloadbtnID,backgroundImageID;
    private String imagePath = null;
    private String path_background = null;
    private int background_customPath = 0;
    private String path_Crop = null;
    private String poetry_text = null;
    private String poetry_text_current = null;
    private BubbleInputDialog mBubbleInputDialog;
    private StickerViewNewLibraryChina mCurrentView;
    private LinearLayout bottomViewID,anotherBottomViewID;
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
    private String image_path = null;
    private String poetry_text_internal = null;
    SeekBar blurBar;
    int blurValue = 1;
    Bitmap bitmap_Source;
    private Button colourID;
    StickerTextView tv_sticker,tv_sticker2;
    public boolean isClickedFirstTime = true;
    public static final int REQUEST_CODE_ONE = 1;
    public static final int REQUEST_CODE_TWO = 2;
    public static final int REQUEST_CODE_THREE = 3;
    private ImageView backlanguage;
    private RecyclerView horizontalRecylcerViewID;
    private List<Colors> colorList;
    private HorizontalAdapter horizontalAdapter;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry_blend);
        mContentRootView = (RelativeLayout) findViewById(R.id.rl_content_root);
        backgroundImageID = findViewById(R.id.backgroundImageID);
        //poetryActivityID = findViewById(R.id.poetryActivityID);
        backlanguage = findViewById(R.id.backlanguage);
        colourID = findViewById(R.id.colourID);
        blendActivtyID = findViewById(R.id.blendActivtyID);
        blurBar  = findViewById(R.id.blurBar);
        anotherBottomViewID = findViewById(R.id.anotherBottomViewID);
        mViews = new ArrayList<>();
        galleyID = findViewById(R.id.galleyID);
        backgroundID = findViewById(R.id.backgroundID);
        downloadbtnID = findViewById(R.id.downloadbtnID);
        bottomViewID = findViewById(R.id.bottomViewID);
        horizontalRecylcerViewID = findViewById(R.id.horizontalRecylcerViewID);
        checkedID = findViewById(R.id.checkedID);
        colorList = new ArrayList<Colors>();
        if (getIntent().getStringExtra("name").compareTo("text") == 0) {
            poetry_text = getIntent().getStringExtra("poetry_text");
            if (poetry_text != null && !poetry_text.isEmpty()) {
                tv_sticker = new StickerTextView(PoetryBlendActivity.this);
                tv_sticker.setText(poetry_text);
                mContentRootView.addView(tv_sticker);
            }
        }else if(getIntent().getStringExtra("name").compareTo("custom_keyboard_text") == 0){
            poetry_text = getIntent().getStringExtra("poetry_text");
            if (poetry_text != null && !poetry_text.isEmpty()) {
                tv_sticker = new StickerTextView(PoetryBlendActivity.this);
                tv_sticker.setText(poetry_text);
                mContentRootView.addView(tv_sticker);
            }
        }

        galleyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PoetryBlendActivity.this,GalleryPoetryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ONE);
            }
        });

        backgroundID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PoetryBlendActivity.this,CustomBackgroundPoetryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TWO);
            }
        });

        downloadbtnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomViewID.setVisibility(View.GONE);
                if (anotherBottomViewID.getVisibility() == View.VISIBLE){
                    anotherBottomViewID.setVisibility(View.GONE);
                }
                if(tv_sticker != null){
                    tv_sticker.setControlItemsHidden(true);
                }
                generateBitmap();
            }
        });

        mContentRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isClickedFirstTime){
                    if(tv_sticker != null) {
                        tv_sticker.setControlItemsHidden(true);
                        isClickedFirstTime = false;
                    }
                }else {
                    if(tv_sticker != null) {
                        tv_sticker.setControlItem();
                        isClickedFirstTime = true;
                    }
                }
            }
        });

        colorList.add(new Colors(getResources().getColor(R.color.black_color)));
        colorList.add(new Colors(getResources().getColor(R.color.white)));
        colorList.add(new Colors(getResources().getColor(R.color.darkblack_color)));
        colorList.add(new Colors(getResources().getColor(R.color.gray_color)));
        colorList.add(new Colors(getResources().getColor(R.color.red)));
        colorList.add(new Colors(getResources().getColor(R.color.green)));
        colorList.add(new Colors(getResources().getColor(R.color.blue)));
        colorList.add(new Colors(getResources().getColor(R.color.maroon)));
        colorList.add(new Colors(getResources().getColor(R.color.purple)));
        colorList.add(new Colors(getResources().getColor(R.color.silver)));
        colorList.add(new Colors(getResources().getColor(R.color.pink)));
        colorList.add(new Colors(getResources().getColor(R.color.olive)));
        colorList.add(new Colors(getResources().getColor(R.color.teal)));


        horizontalAdapter=new HorizontalAdapter(colorList, PoetryBlendActivity.this, PoetryBlendActivity.this);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(PoetryBlendActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecylcerViewID.setLayoutManager(horizontalLayoutManager);
        horizontalRecylcerViewID.setAdapter(horizontalAdapter);



        colourID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomViewID.setVisibility(View.GONE);
                anotherBottomViewID.setVisibility(View.VISIBLE);
            }
        });

        backlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkedID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomViewID.setVisibility(View.VISIBLE);
                anotherBottomViewID.setVisibility(View.GONE);
            }
        });
    }

    public void changeTextColour(int color){
        tv_sticker.setTextCclor(color);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_ONE  && resultCode  == RESULT_OK) {
                path_background = data.getStringExtra("image_path");
                backgroundImageID.setImageURI(Uri.parse(path_background));
            }else if (requestCode == REQUEST_CODE_TWO && resultCode == RESULT_OK) {
                int requiredValue = data.getIntExtra("image_path", 0);
                //backgroundImageID.setBackgroundResource(requiredValue);
                backgroundImageID.setImageResource(requiredValue);
            }
//            }else if (requestCode == REQUEST_CODE_THREE && resultCode == RESULT_OK){
//                poetry_text_current = data.getStringExtra("poetry_text");
//                tv_sticker2 = new StickerTextView(PoetryBlendActivity.this);
//                tv_sticker2.setText(poetry_text_current);
//                mContentRootView.addView(tv_sticker2);
//            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }


    public static Bitmap decodeFile(String photoPath){
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

    private void addBubblePrefrences(String poetry_text) {
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
            //finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
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

}
