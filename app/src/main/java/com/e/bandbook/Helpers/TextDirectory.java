package com.e.bandbook.Helpers;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import android.content.*;

import com.e.bandbook.Observer.SongsListObserver;


import androidx.annotation.RequiresApi;

import io.reactivex.rxjava3.core.Observable;


public class TextDirectory {
    File[] files;
    Context mContext;
    ArrayList<Song> unsortedSongs = new ArrayList<>();
    File directory;


    public TextDirectory(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("BANDbook", Context.MODE_PRIVATE);
        this.mContext = context;
        this.directory = new File(sharedPref.getString("dir", "BRAK"));
        files = directory.listFiles();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Song> getSortedTextList(ArrayList<Song> songs, int sortBy) {
        songs.clear();
        unsortedSongs.clear();

        Observable.fromArray(files)
                .subscribe(file -> unsortedSongs.add(new Song(file)));

        if (sortBy == 1) {
            Observable.fromIterable(unsortedSongs)
                    .sorted(Comparator.comparing(song -> song.title))
                    .subscribe(new SongsListObserver(songs));
        } else {
            Observable.fromIterable(unsortedSongs)
                    .sorted(Comparator.comparing(song -> song.constructor))
                    .subscribe(new SongsListObserver(songs));
        }
        return songs;
    }

    public File[] getTextTitles() {
        return files;
    }


    public static String getPath(String path) {
        try {
            if (path != null) {
                path = path.split(":")[1];
            }
        } catch (Exception e) {
        }

        return Environment.getExternalStorageDirectory() + "/" + path;
    }

}
