package com.e.bandbook.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e.bandbook.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;



public class RecycleViewHolder extends RecyclerView.ViewHolder {

    CircleImageView circleImgView;
    TextView textView;
    ConstraintLayout layout;


    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        circleImgView = itemView.findViewById(R.id.circle_img);
        textView = itemView.findViewById(R.id.textView);
        layout = itemView.findViewById(R.id.panel_item);

    }
}
