package com.arce.angel.request.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arce.angel.request.Models.SongModel;
import com.arce.angel.request.R;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Angel on 10/04/2016.
 */
public class SongAdapter extends ArrayAdapter<SongModel> {

    Context context;

    public SongAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SongWrapper wrapper;
        View rootView = convertView;

        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.row_canciones, parent, false);
            wrapper = new SongWrapper();

            wrapper.imageSong = (CircularImageView) rootView.findViewById(R.id.imageSong);
            wrapper.nameSong = (TextView) rootView.findViewById(R.id.nameSong);
            wrapper.nameArtist = (TextView) rootView.findViewById(R.id.nameArtist);

            rootView.setTag(wrapper);
        } else {
            wrapper = (SongWrapper) rootView.getTag();
        }

        Picasso.with(context).load(getItem(position).getURLImageSong()).placeholder(R.mipmap.ic_av_queue_music).error(R.mipmap.ic_av_queue_music).into(wrapper.imageSong);
        wrapper.nameSong.setText(getItem(position).getNameSong());
        wrapper.nameArtist.setText(getItem(position).getNameArtist());

        return rootView;
    }

    public class SongWrapper {
        CircularImageView imageSong;
        TextView nameSong;
        TextView nameArtist;
    }
}
