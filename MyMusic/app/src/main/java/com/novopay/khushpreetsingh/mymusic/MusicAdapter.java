package com.novopay.khushpreetsingh.mymusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class MusicAdapter extends BaseAdapter {
    WeakReference<Context> contextWeakReference;
    List<Music> musicList;
    public MusicAdapter(Context context, List<Music> musicList) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.musicList = musicList;

    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Music getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView albumView;
        TextView artistView;
        TextView songView;
        ImageView imageView;

    }
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(contextWeakReference.get());
            view = layoutInflater.inflate(R.layout.item_music, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.albumView = (TextView) view.findViewById(R.id.item_music_album);
            viewHolder.artistView = (TextView) view.findViewById(R.id.item_music_artist);
            viewHolder.songView = (TextView) view.findViewById(R.id.item_music_song);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.item_music_image);

            view.setTag(viewHolder);
        }

    if(viewHolder == null){
        viewHolder = (ViewHolder) view.getTag();
    }

        Music music = getItem(position);

        viewHolder.albumView.setText(music.getAlbumName());
        viewHolder.artistView.setText(music.getSingerName());
        viewHolder.songView.setText(music.getSongName());
        Picasso.with(contextWeakReference.get()).load(music.getImageUrl()).error(R.drawable.thumb).into(viewHolder.imageView);



        return view;
    }
}
