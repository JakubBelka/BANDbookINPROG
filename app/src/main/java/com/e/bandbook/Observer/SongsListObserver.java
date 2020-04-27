package com.e.bandbook.Observer;

import com.e.bandbook.Helpers.Song;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SongsListObserver implements Observer<Song> {

    ArrayList<Song> songs;

    public SongsListObserver(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull Song song) {
        songs.add(song);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
