package com.e.bandbook.RecyclerView;

import android.view.View;
import android.content.Context;
import android.content.*;

import com.e.bandbook.SettingsActivity;
import com.e.bandbook.TextListActivity;

public class OnHolderClickListener implements View.OnClickListener {
    private String name;
    private Context mContext;

    public OnHolderClickListener(String name, Context context) {
        this.name = name;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        if(name.equals("Teksty")){
            Intent intent = new Intent(mContext, TextListActivity.class);
            mContext.startActivity(intent);
        }
        else if(name.equals("Ustawienia")){
            Intent intent = new Intent(mContext, SettingsActivity.class);
            mContext.startActivity(intent);
        }


    }
}
