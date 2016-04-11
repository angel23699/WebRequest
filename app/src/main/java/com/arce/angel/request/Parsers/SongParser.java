package com.arce.angel.request.Parsers;

import com.arce.angel.request.Models.SongModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Angel on 10/04/2016.
 */
public class SongParser {

    private static final String KEY_URL_IMAGE_SONG = "url";
    private static final String KEY_NAME_ARTIST = "artistas";
    private static final String KEY_NAME_SONG = "canciones";

    public static ArrayList<SongModel> parseJSONFromString(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        ArrayList<SongModel> dataSet = new ArrayList<>();
        SongModel song = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            song = new SongModel();
            song.setURLImageSong(jsonObject.getString(KEY_URL_IMAGE_SONG));
            song.setNameSong(jsonObject.getString(KEY_NAME_SONG));
            song.setNameArtist(jsonObject.getString(KEY_NAME_ARTIST));
            dataSet.add(song);
        }

        return dataSet;
    }
}
