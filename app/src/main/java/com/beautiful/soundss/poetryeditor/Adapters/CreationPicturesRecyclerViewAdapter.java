package com.beautiful.soundss.poetryeditor.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Activities.CustomBackgroundPoetryActivity;
import com.beautiful.soundss.poetryeditor.Activities.FullCreationPictureActivity;
import com.beautiful.soundss.poetryeditor.Model.Background;
import com.beautiful.soundss.poetryeditor.Model.Creation;
import com.beautiful.soundss.poetryeditor.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreationPicturesRecyclerViewAdapter extends RecyclerView.Adapter<CreationPicturesRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private boolean showingFirst = true;
    private List<Creation> creationList;
    public  int countPhotos = 0;
    private ArrayList<Creation> backgroundArrayList   = new ArrayList<>();
    public static final String CREATION_IMAGE = "creation_image";
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;

    public CreationPicturesRecyclerViewAdapter(final Context context, List<Creation> creationList) {
        this.context = context;
        this.creationList = creationList;
    }

    @NonNull
    @Override
    public CreationPicturesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photosfolder_creation,parent,false);
        return new CreationPicturesRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull CreationPicturesRecyclerViewAdapter.ViewHolder holder, final int position) {

        final Creation creation = creationList.get(position);
        holder.thumbImage.setImageURI(Uri.parse(creation.getImagePath()));
    }

    @Override
    public int getItemCount() {
        return creationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_foldern, tv_folder2;
        ImageView thumbImage;
        private Button sharebtn, deletebtn;

        public ViewHolder(final View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            tv_foldern = (TextView) itemView.findViewById(R.id.tv_folder);
            tv_folder2 = (TextView) itemView.findViewById(R.id.tv_folder2);
            thumbImage = (ImageView) itemView.findViewById(R.id.thumbImageNail);
            sharebtn = (Button) itemView.findViewById(R.id.sharebtn);
            deletebtn = (Button) itemView.findViewById(R.id.deletebtn);
            sharebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Creation creation = creationList.get(pos);
                    Uri imageUri = Uri.parse(creation.getImagePath());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/.jpg");
                    intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    context.startActivity(Intent.createChooser(intent, "Share"));
                }
            });

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Creation creation = creationList.get(pos);
                    final String path = Environment.getExternalStorageDirectory() + "/saved_images/" + creation.getImage();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you Sure you want to delete it?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user pressed "yes", then he is allowed to exit from application
                            File f = new File(path);
                            boolean d = f.delete();
                            if (d) {
                                Toast.makeText(ctx, "File Deleted", Toast.LENGTH_SHORT).show();
                                creationList.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                            } else {
                                Toast.makeText(ctx, "Failue!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //if user select "No", just cancel this dialog and continue with app
                            //Toast.makeText(MainActivity.this, " i am no", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Creation creation = creationList.get(pos);
                    Intent intent = new Intent(context, FullCreationPictureActivity.class);
                    intent.putExtra(CREATION_IMAGE,creation.getImagePath());
                    context.startActivity(intent);
                }
            });
        }




    }


}
