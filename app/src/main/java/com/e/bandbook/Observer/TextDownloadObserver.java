package com.e.bandbook.Observer;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.e.bandbook.Helpers.Song;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TextDownloadObserver implements Observer<StorageReference> {
    private final Context mContext;
    File[] file;
    StorageReference storageReference;
    SharedPreferences sharedPref;


    public TextDownloadObserver(android.content.Context context, File[] file, StorageReference storageReference) {
        this.file = file;
        this.storageReference = storageReference;
        this.mContext = context;
        sharedPref = context.getSharedPreferences("BANDbook", Context.MODE_PRIVATE);
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull StorageReference storageReference) {
        if (!isInDirectory(storageReference.getName())) {

        }
        downloadText(storageReference.getName());

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    boolean isInDirectory(String s) {
        boolean ret = false;

        for (File file : file) {
            if (file.getName().equals(s)) {
                ret = true;
            }
        }
        return ret;
    }

    private void downloadText(String name) {
        StorageReference ref = storageReference.child(name);

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                download(name, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@androidx.annotation.NonNull Exception e) {
                System.out.println("=======================fail");
                e.printStackTrace();
            }
        });
    }

    void download(String filename, String url) {
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(mContext, sharedPref.getString("dir", "BRAK"), filename);

        downloadManager.enqueue(request);
    }
}
