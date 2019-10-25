package com.beautiful.soundss.poetryeditor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautiful.soundss.poetryeditor.Activities.BlendImagesActivity;
import com.beautiful.soundss.poetryeditor.Activities.BlendImagesInternalActivity;
import com.beautiful.soundss.poetryeditor.Model.Background;
import com.beautiful.soundss.poetryeditor.Model.Model_images;
import com.beautiful.soundss.poetryeditor.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ArslanNasr on 7/6/2018.
 */

public class BlendImagesInternalRecyclerViewAdapter extends RecyclerView.Adapter<BlendImagesInternalRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private boolean showingFirst = true;
    private ArrayList<Model_images> images;
    public  int countPhotos = 0;
    private ArrayList<Model_images> photosArrayList   = new ArrayList<>();
    BlendImagesInternalActivity blendImagesActivity;
    private int lastCheckedPosition = -1;

    public BlendImagesInternalRecyclerViewAdapter(final Context context, ArrayList<Model_images> images, BlendImagesInternalActivity blendImagesActivity) {
        this.context = context;
        this.images = images;
        this.blendImagesActivity = blendImagesActivity;
    }

    @NonNull
    @Override
    public BlendImagesInternalRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photosfolder,parent,false);
        return new BlendImagesInternalRecyclerViewAdapter.ViewHolder(view,context,blendImagesActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull BlendImagesInternalRecyclerViewAdapter.ViewHolder holder, final int position) {

        final Model_images model_images = images.get(position);
        //holder.poetry_textID.setText(poetry.getPoetry());
        holder.tv_foldern.setVisibility(View.GONE);
        holder.tv_folder2.setVisibility(View.GONE);
        //images.get(position)
        Glide.with(context).load(model_images.getStr_ImagePath())
                .placeholder(R.drawable.ic_launcher_background).centerCrop()
                .into(holder.thumbImage);
        holder.itemCheckBox.setChecked(position == lastCheckedPosition);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_foldern, tv_folder2;
        ImageView thumbImage;
        CheckBox itemCheckBox;
        BlendImagesInternalActivity blendImagesActivity;

        public ViewHolder(final View itemView, final Context ctx, final BlendImagesInternalActivity blendImagesActivity) {
            super(itemView);
            context = ctx;
            this.blendImagesActivity = blendImagesActivity;
            tv_foldern = (TextView) itemView.findViewById(R.id.tv_folder);
            tv_folder2 = (TextView) itemView.findViewById(R.id.tv_folder2);
            thumbImage = (ImageView) itemView.findViewById(R.id.thumbImageNail);
            thumbImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastCheckedPosition = getAdapterPosition();
                    Model_images model_images = images.get(lastCheckedPosition);
                    blendImagesActivity.setValue("1/1");
                    blendImagesActivity.ImageURI(model_images.getStr_ImagePath());
                    notifyDataSetChanged();
//                    itemCheckBox.setChecked(true);
                }
            });
            itemCheckBox = (CheckBox) itemView.findViewById(R.id.itemCheckBox);

            itemCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lastCheckedPosition = getAdapterPosition();
                    Model_images model_images = images.get(lastCheckedPosition);
                    blendImagesActivity.setValue("1/1");
                    blendImagesActivity.ImageURI(model_images.getStr_ImagePath());
                    notifyDataSetChanged();
                }
            });

        }
    }
}
