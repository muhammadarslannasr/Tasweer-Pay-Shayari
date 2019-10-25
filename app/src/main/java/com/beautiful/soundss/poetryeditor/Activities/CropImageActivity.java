package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.R;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CropImageActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private String imagePath = null;
    private int  resultCode = 9162;
    private int requestCode = 6709;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);


        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Crop.pickImage(CropImageActivity.this);

            }
        });

        if (getIntent().getStringExtra("name").compareTo("imagePath") == 0) {
            imagePath = getIntent().getStringExtra("image_path");
        }

        if(resultCode == Crop.REQUEST_PICK){
            Uri source_uri = Uri.fromFile(new File(imagePath));
            Uri destination_uri = Uri.fromFile(new File(getCacheDir(),"cropped"));
            Crop.of(source_uri,destination_uri).asSquare().start(CropImageActivity.this);
//            imageView.setImageURI(Crop.getOutput(data));
//            imageView.setImageURI(Crop.);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == Crop.REQUEST_PICK){
                //Uri source_uri = data.getData();
//                Uri source_uri = data.getData();
//                //Toast.makeText(this, source_uri+"", Toast.LENGTH_SHORT).show();
//                Uri destination_uri = Uri.fromFile(new File(getCacheDir(),"cropped"));
//                //Toast.makeText(this, destination_uri+"", Toast.LENGTH_SHORT).show();
//                Crop.of(source_uri,destination_uri).asSquare().start(CropImageActivity.this);
                //imageView.setImageURI(Crop.getOutput(data));
            }else if(requestCode == Crop.REQUEST_CROP){
                handle_crop(resultCode,data);
            }
        }
    }

    private void handle_crop(int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver() ,Crop.getOutput(data));
                String returnPath = SaveImage(bitmap);
                String path = Environment.getExternalStorageDirectory()+ "/saved_images/"+returnPath;
                File imgFile = new  File(path);
                if(imgFile.exists()){
                    //Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CropImageActivity.this, BlendActivity.class);
                    intent.putExtra("name", "imagePath_Crop");
                    intent.putExtra("image_path_crop", path);
                    intent.putExtra("image_path_crop_1", imagePath);
                    startActivity(intent);
                    finish();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(resultCode == Crop.RESULT_ERROR){
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
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
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();
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



}
