package com.beautiful.soundss.poetryeditor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautiful.soundss.poetryeditor.Activities.PoetryBlendActivity;
import com.beautiful.soundss.poetryeditor.Model.Colors;
import com.beautiful.soundss.poetryeditor.R;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    List<Colors> colorsList;
    Context context;
    PoetryBlendActivity poetryBlendActivity;

    public HorizontalAdapter(List<Colors> colorsList, Context context,PoetryBlendActivity poetryBlendActivity) {
        this.colorsList = colorsList;
        this.context = context;
        this.poetryBlendActivity = poetryBlendActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtview;
        public MyViewHolder(View view) {
            super(view);
            imageView=(ImageView) view.findViewById(R.id.imageview);
            txtview=(TextView) view.findViewById(R.id.txtview);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.colourrow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.imageView.setBackgroundColor(colorsList.get(position).getTextColor());
        //holder.txtview.setText(horizontalList.get(position).txt);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //String list = horizontalList.get(position).txt.toString();
               poetryBlendActivity.changeTextColour(colorsList.get(position).getTextColor());
            }

        });

    }

    @Override
    public int getItemCount()
    {
        return colorsList.size();
    }



}
