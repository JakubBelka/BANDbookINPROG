package com.e.bandbook.Helpers;

import java.io.File;

public class Song implements Comparable {
    public String title;
    public String constructor;
    public File file;

    public Song(File file) {
        this.file = file;

        try {
            String[] name = file.getName().split("-");
            this.title = name[1];
            this.constructor = name[0];

        } catch (Exception e){
            this.title = file.getName();
            this.constructor = file.getName();
        }
    }

    @Override
    public int compareTo(Object o) {
        return title.compareTo(o.toString());
    }
}
