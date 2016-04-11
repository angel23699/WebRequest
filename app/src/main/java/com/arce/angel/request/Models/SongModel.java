package com.arce.angel.request.Models;

import android.graphics.Bitmap;

/**
 * Created by Angel on 10/04/2016.
 */
public class SongModel {

    String URLImageSong;
    String NameSong;
    String NameArtist;

    public String getURLImageSong() {
        return URLImageSong;
    }

    public void setURLImageSong(String URLImageSong) {
        this.URLImageSong = URLImageSong;
    }

    public String getNameSong() {
        return NameSong;
    }

    public void setNameSong(String nameSong) {
        NameSong = nameSong;
    }

    public String getNameArtist() {
        return NameArtist;
    }

    public void setNameArtist(String nameArtist) {
        NameArtist = nameArtist;
    }
}
