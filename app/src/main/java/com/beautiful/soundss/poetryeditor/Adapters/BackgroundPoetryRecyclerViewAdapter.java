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

import com.beautiful.soundss.poetryeditor.Activities.BackgroundImageActivity;
import com.beautiful.soundss.poetryeditor.Activities.CustomBackgroundPoetryActivity;
import com.beautiful.soundss.poetryeditor.Model.Background;
import com.beautiful.soundss.poetryeditor.R;

import java.util.ArrayList;
import java.util.List;
public class BackgroundPoetryRecyclerViewAdapter extends RecyclerView.Adapter<BackgroundPoetryRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private boolean showingFirst = true;
    private List<Background> backgrounds;
    public  int countPhotos = 0;
    private ArrayList<Background> backgroundArrayList   = new ArrayList<>();
    CustomBackgroundPoetryActivity customBackgroundPoetryActivity;
    private int lastCheckedPosition = -1;

    public BackgroundPoetryRecyclerViewAdapter(final Context context, List<Background> backgrounds, CustomBackgroundPoetryActivity customBackgroundPoetryActivity) {
        this.context = context;
        this.backgrounds = backgrounds;
        this.customBackgroundPoetryActivity = customBackgroundPoetryActivity;
    }

    @NonNull
    @Override
    public BackgroundPoetryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_photosfolder,parent,false);
        return new BackgroundPoetryRecyclerViewAdapter.ViewHolder(view,context,customBackgroundPoetryActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull BackgroundPoetryRecyclerViewAdapter.ViewHolder holder, final int position) {

        final Background background = backgrounds.get(position);
        //holder.poetry_textID.setText(poetry.getPoetry());
        holder.tv_foldern.setVisibility(View.GONE);
        holder.tv_folder2.setVisibility(View.GONE);
        holder.thumbImage.setImageResource(background.getImage());
        holder.itemCheckBox.setChecked(position == lastCheckedPosition);
    }

    @Override
    public int getItemCount() {
        return backgrounds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_foldern, tv_folder2;
        ImageView thumbImage;
        CheckBox itemCheckBox;
        CustomBackgroundPoetryActivity customBackgroundPoetryActivity;

        public ViewHolder(final View itemView, final Context ctx, final CustomBackgroundPoetryActivity customBackgroundPoetryActivity) {
            super(itemView);
            context = ctx;
            this.customBackgroundPoetryActivity = customBackgroundPoetryActivity;
            tv_foldern = (TextView) itemView.findViewById(R.id.tv_folder);
            tv_folder2 = (TextView) itemView.findViewById(R.id.tv_folder2);
            thumbImage = (ImageView) itemView.findViewById(R.id.thumbImageNail);
            thumbImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastCheckedPosition = getAdapterPosition();
                    Background background = backgrounds.get(lastCheckedPosition);
                    customBackgroundPoetryActivity.setValue("1/1");
                    customBackgroundPoetryActivity.ImageURI(background.getImage());
                    notifyDataSetChanged();
//                    itemCheckBox.setChecked(true);
                }
            });
            itemCheckBox = (CheckBox) itemView.findViewById(R.id.itemCheckBox);

            itemCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lastCheckedPosition = getAdapterPosition();
                    Background background = backgrounds.get(lastCheckedPosition);
                    customBackgroundPoetryActivity.setValue("1/1");
                    customBackgroundPoetryActivity.ImageURI(background.getImage());
                    notifyDataSetChanged();
                }
            });

        }
    }
}
