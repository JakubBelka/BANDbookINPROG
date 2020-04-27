package com.e.bandbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.e.bandbook.RecyclerView.Items;
import com.e.bandbook.RecyclerView.RecycleViewAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.core.Observable;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        Items items = new Items();
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(items.getItemsInformation(),
                items.getItemsImages(),
                items.getItemsColors(),
                this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getSharedPreferences("BANDbook",Context.MODE_PRIVATE);
        String highScore = sharedPref.getString("dir", "BRAK");
        Log.d("TAGGGGGGG", "================================" + highScore);
    }
}
