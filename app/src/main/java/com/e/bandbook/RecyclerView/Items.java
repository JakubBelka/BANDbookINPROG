package com.e.bandbook.RecyclerView;

import android.graphics.Color;

import com.e.bandbook.R;

import java.util.ArrayList;

public class Items {

    ArrayList<String> itemsInformation = new ArrayList<>();
    ArrayList<Integer> itemsImages = new ArrayList<>();
    ArrayList<Integer> itemsColors = new ArrayList<>();


    public Items() {
        itemsInformation.add("Teksty");
        itemsImages.add(R.drawable.text_foreground);
        itemsColors.add(R.color.colorPanel1);

        itemsInformation.add("Połącz");
        itemsImages.add(R.drawable.connect_foreground);
        itemsColors.add(R.color.colorPanel2);


        itemsInformation.add("Alarmy");
        itemsImages.add(R.drawable.alarm_foreground);
        itemsColors.add(R.color.colorPanel3);


        itemsInformation.add("Ustawienia");
        itemsImages.add(R.drawable.settings_foreground);
        itemsColors.add(R.color.colorPanel4);
    }

    public ArrayList<String> getItemsInformation() {
        return itemsInformation;
    }

    public ArrayList<Integer> getItemsImages() {
        return itemsImages;
    }

    public ArrayList<Integer> getItemsColors() {
        return itemsColors;
    }
}
