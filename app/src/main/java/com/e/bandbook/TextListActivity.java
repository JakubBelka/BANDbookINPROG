package com.e.bandbook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.e.bandbook.Helpers.Song;
import com.e.bandbook.Helpers.TextDirectory;
import com.e.bandbook.TextList.TextListAdapter;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TextListActivity extends AppCompatActivity {

    @BindView(R.id.text_recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.sort_by_contractor_button)
    Button sortByConstractorBtn;
    @BindView(R.id.sort_by_title_button)
    Button sortByTitleBtn;
    @BindView(R.id.sort_panel)
    LinearLayout sortPanel;
    @BindView(R.id.search_panel)
    LinearLayout searchPanel;
    @BindView(R.id.less_option_button)
    ImageButton lessOptionsBtn;
    @BindView(R.id.search_button)
    ImageButton searchButton;
    @BindView(R.id.search_edit_text)
    EditText searchEditText;

    private TextDirectory textDirectory;
    private TextListAdapter textListAdapter;
    private ArrayList<Song> songs;
    private CompositeDisposable compositeDisposable;
    private Disposable disposable;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_list);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();
        SharedPreferences sharedPref = this.getSharedPreferences("BANDbook", Context.MODE_PRIVATE);

        textDirectory = new TextDirectory(this);
        songs = new ArrayList<>();
        songs = textDirectory.getSortedTextList(songs, 2);

        textListAdapter = new TextListAdapter(songs, this);
        recyclerView.setAdapter(textListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        disposable = RxTextView.textChanges(searchEditText)
                .flatMapSingle(this::findSongs)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                            songs.clear();
                            songs.addAll(item);
                            textListAdapter.notifyDataSetChanged();
                        });

                        compositeDisposable.add(disposable);
    }

    private Single<List<Song>> findSongs(CharSequence input) {
        return Observable.fromIterable(songs)
                .filter(item -> item.file
                        .getName()
                        .toLowerCase()
                        .contains(input.toString().toLowerCase()))
                .distinct()
                .toSortedList();

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

    @OnClick(R.id.search_button)
    void showMoreOptions() {
        searchPanel.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.less_option_button)
    void showLessOptions() {
        searchPanel.setVisibility(View.GONE);
        searchButton.setVisibility(View.VISIBLE);

    }


}
