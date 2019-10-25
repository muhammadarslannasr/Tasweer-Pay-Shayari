package com.beautiful.soundss.poetryeditor.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.R;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import static com.beautiful.soundss.poetryeditor.Adapters.CreationPicturesRecyclerViewAdapter.CREATION_IMAGE;

public class FullCreationPictureActivity extends AppCompatActivity {

    private ImageView fullCreationID;
    private String creationPath = null;
    private ImageView shareCreationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_creation_picture);

        fullCreationID  = findViewById(R.id.fullCreationID);
        shareCreationID = findViewById(R.id.shareCreationID);
        creationPath = getIntent().getStringExtra(CREATION_IMAGE);
        fullCreationID.setImageURI(Uri.parse(creationPath));
        shareCreationID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri imageUri = Uri.parse(creationPath);
                Intent intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/.jpg");
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

    }
}
