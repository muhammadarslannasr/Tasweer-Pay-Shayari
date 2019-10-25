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
import android.widget.TextView;

import com.beautiful.soundss.poetryeditor.Activities.PoetryBlendActivity;
import com.beautiful.soundss.poetryeditor.Model.Poetry;
import com.beautiful.soundss.poetryeditor.R;

import java.util.List;
public class PoetryTextRecyclerViewAdapter extends RecyclerView.Adapter<PoetryTextRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Poetry> poetryList;
    private boolean showingFirst = true;
    private String poetry_text_current = null;


    public PoetryTextRecyclerViewAdapter(final Context context, List<Poetry> poetryList) {
        this.context = context;
        this.poetryList = poetryList;
    }

    @NonNull
    @Override
    public PoetryTextRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poetry_row,parent,false);
        return new PoetryTextRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull PoetryTextRecyclerViewAdapter.ViewHolder holder, int position) {

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
                    Intent intent = new Intent();
                    intent.putExtra("poetry_text",poetry.getPoetry());
                    ((Activity)context).setResult(Activity.RESULT_OK,intent);
                    ((Activity)context).finish();
                }
            });

        }
    }
}
