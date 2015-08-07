package com.novopay.khushpreetsingh.mymusic;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.khushpreetsingh.mymusic.provider.MusicSQLiteOpenHelper;
import com.squareup.picasso.Picasso;

/**
 * Created by khushpreetsingh on 8/6/15.
 */
public class MusicCursor extends CursorAdapter {
    public MusicCursor(Context context, Cursor c) {
        super(context, c, false);
    }

    public class ViewHolder{
        TextView albumView;
        TextView artistView;
        TextView songView;
        ImageView imageView;

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_music, parent, false);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.albumView = (TextView) view.findViewById(R.id.item_music_album);
        viewHolder.artistView = (TextView) view.findViewById(R.id.item_music_artist);
        viewHolder.songView = (TextView) view.findViewById(R.id.item_music_song);
        viewHolder.imageView = (ImageView) view.findViewById(R.id.item_music_image);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder= (ViewHolder) view.getTag();

        String imageUrl = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.IMAGE_URL));
        String songName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));
        //String artistName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ARTIST_NAME));
        String albumName = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM));

        //viewHolder.artistView.setText(artistName);
        viewHolder.albumView.setText(albumName);
        Picasso.with(context).load(imageUrl).into(viewHolder.imageView);
        viewHolder.songView.setText(songName);
       // Toast.makeText(context, "This is an Event " +imageUrl , Toast.LENGTH_LONG).show();
    }
}
