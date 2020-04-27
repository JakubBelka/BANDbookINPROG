package com.e.bandbook.TextList;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e.bandbook.R;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextHolder extends RecyclerView.ViewHolder {


    TextView songTitle;
    TextView textNumber;
    RelativeLayout layout;
    File song;

    public TextHolder(@NonNull View itemView) {
        super(itemView);
        songTitle = itemView.findViewById(R.id.song_title);
        layout = itemView.findViewById(R.id.song_panel);
    }
}
