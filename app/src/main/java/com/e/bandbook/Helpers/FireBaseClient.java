package com.e.bandbook.Helpers;

import android.util.Log;
import android.widget.Toast;

import com.e.bandbook.Observer.TextDownloadObserver;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Context;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.concurrent.Executor;


import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FireBaseClient {

    private StorageReference storageReference;

    public void downloadTexts(android.content.Context context) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
        } else {
            mAuth.signInAnonymously();
        }



        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult result) {
                Observable.fromIterable(result.getItems())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new TextDownloadObserver(context, new TextDirectory(context).getTextTitles(), storageReference));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception exception) {
                exception.printStackTrace();
                Toast.makeText(context, "Błąd. Sprawdź połączenie z internetem", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
