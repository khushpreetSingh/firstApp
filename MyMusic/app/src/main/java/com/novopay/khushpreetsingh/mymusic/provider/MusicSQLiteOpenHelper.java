package com.novopay.khushpreetsingh.mymusic.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.List;

/**
 * Created by khushpreetsingh on 8/5/15.
 */
public class MusicSQLiteOpenHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String DATABASE_NAME = "musicdb";

    public interface Tables{
        String MUSIC = "music";
    }

    public interface TableMusic{
        String IMAGE_URL = "imageurl";
        String ALBUM = "album";
        String ARTIST_NAME = "artistname";
        String SONG = "song";
    }

    public MusicSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    final String CREATE_TABLE_MUSIC = "create table " + Tables.MUSIC + " ("
            + BaseColumns._ID +  " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableMusic.IMAGE_URL + " TEXT NOT NULL,"
            + TableMusic.ALBUM + " TEXT NOT NULL, "
            + TableMusic.ARTIST_NAME + "TEXT,"
            + TableMusic.SONG + " TEXT NOT NULL);";

    final String PUT_DATA = "insert into " + Tables.MUSIC + " values(1,\"pic1\",\"Album1\",\"Artist1\",\"song1\")";
    final String PUT_DATA2 = "insert into " + Tables.MUSIC + " values(2,\"pic2\",\"Album2\",\"Artist2\",\"song2\")";
    final String PUT_DATA3 = "insert into " + Tables.MUSIC + " values(3,\"pic3\",\"Album3\",\"Artist3\",\"song3\")";
    final String PUT_DATA4 = "insert into " + Tables.MUSIC + " values(4,\"pic4\",\"Album4\",\"Artist4\",\"song4\")";
    final String PUT_DATA5 = "insert into " + Tables.MUSIC + " values(5,\"pic5\",\"Album5\",\"Artist5\",\"song5\")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MUSIC);
        db.execSQL(PUT_DATA);
        db.execSQL(PUT_DATA2);
        db.execSQL(PUT_DATA3);
        db.execSQL(PUT_DATA4);
        db.execSQL(PUT_DATA5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
