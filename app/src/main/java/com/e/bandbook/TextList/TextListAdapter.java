package com.e.bandbook.TextList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.*;

import com.e.bandbook.Helpers.Song;
import com.e.bandbook.R;
import com.e.bandbook.RecyclerView.RecycleViewHolder;
import com.e.bandbook.SongActivity;
import com.e.bandbook.TextListActivity;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextListAdapter extends RecyclerView.Adapter<TextHolder> {

    ArrayList<Song> songs = new ArrayList<>();
    private Context mContext;


    public TextListAdapter(ArrayList<Song> songs, Context context) {
        this.songs = songs;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_panel_item, parent, false);
        TextHolder holder = new TextHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {

        holder.songTitle.setText(songs.get(position).file.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SongActivity.class);
                intent.putExtra("file", songs.get(position).file);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
