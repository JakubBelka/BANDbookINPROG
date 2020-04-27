package com.e.bandbook.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.content.Context;

import com.e.bandbook.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private ArrayList<String> mMenuInformation = new ArrayList<>();
    private ArrayList<Integer> mImg = new ArrayList<>();
    private ArrayList<Integer> mColors = new ArrayList<>();
    private Context mContext;


    public RecycleViewAdapter(ArrayList mImgNames, ArrayList mImg, ArrayList mColors, Context mContext) {
        this.mMenuInformation = mImgNames;
        this.mImg = mImg;
        this.mContext = mContext;
        this.mColors = mColors;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_item, parent, false);
        RecycleViewHolder holder = new RecycleViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
         holder.textView.setText(mMenuInformation.get(position));
            holder.circleImgView.setImageResource(mImg.get(position));
            holder.layout.setBackgroundResource(mColors.get(position));
            holder.layout.setOnClickListener(new OnHolderClickListener(mMenuInformation.get(position), mContext));
    }



    @Override
    public int getItemCount() {
        return mImg.size();
    }
}
