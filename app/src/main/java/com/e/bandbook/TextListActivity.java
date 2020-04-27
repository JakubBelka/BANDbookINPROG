package com.e.bandbook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.e.bandbook.Helpers.Song;
import com.e.bandbook.Helpers.TextDirectory;
import com.e.bandbook.TextList.TextListAdapter;

import java.util.ArrayList;

public class TextListActivity extends AppCompatActivity {

    @BindView(R.id.text_recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.sort_by_contractor_button)
    Button sortByConstractorBtn;
    @BindView(R.id.sort_by_title_button)
    Button sortByTitleBtn;
    @BindView(R.id.sort_panel)
    LinearLayout sortPanel;
    @BindView(R.id.more_options_button)
    ImageButton moreOptionsBtn;
    @BindView(R.id.less_options_button)
    ImageButton lessOptionsBtn;

    SharedPreferences sharedPref;
    TextDirectory textDirectory;
    TextListAdapter textListAdapter;

    ArrayList<Song> songs;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_list);
        ButterKnife.bind(this);
        RxJavaPlugins.setErrorHandler(throwable -> {
        });


        sharedPref = this.getSharedPreferences("BANDbook", Context.MODE_PRIVATE);

        textDirectory = new TextDirectory(this);
        songs = new ArrayList<>();
        songs = textDirectory.getSortedTextList(songs, 2);

        textListAdapter = new TextListAdapter(songs, this);
        recyclerView.setAdapter(textListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.sort_by_contractor_button)
    void sortByConstractor() {
        sortByConstractorBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        sortByTitleBtn.setBackgroundColor(getResources().getColor(R.color.inactive_sort_button));

        songs = textDirectory.getSortedTextList(songs, 2);
        textListAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.sort_by_title_button)
    void sortByTitle() {
        sortByTitleBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        sortByConstractorBtn.setBackgroundColor(getResources().getColor(R.color.inactive_sort_button));
        songs = textDirectory.getSortedTextList(songs, 1);
        textListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.more_options_button)
    void showMoreOptions() {
        sortPanel.setVisibility(View.VISIBLE);
        moreOptionsBtn.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.less_options_button)
    void showLessOptions() {
        sortPanel.setVisibility(View.GONE);
        moreOptionsBtn.setVisibility(View.VISIBLE);
    }


}
