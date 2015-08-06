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
        String IMAGE_URL = "imageturl";
        String ALBUM = "album";
        String ARTIST_NAME = "artistname";
        String SONG = "song";
    }

    public MusicSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    final String CREATE_TABLE_MUSIC = "creat table" + Tables.MUSIC + "("
            + BaseColumns._ID +  "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableMusic.IMAGE_URL + " TEXT NOT NULL,"
            + TableMusic.ALBUM + "TEXT NOT NULL"
            + TableMusic.ARTIST_NAME + "TEXT NOT NULL"
            + TableMusic.SONG + "TEXT NOT NULL);";

    final String PUT_DATA = "insert into " + Tables.MUSIC + " values(\"song1\",\"Album1\",\"Artist1\",\"pic1\")";
    final String PUT_DATA2 = "insert into " + Tables.MUSIC + " values(\"song2\",\"Album2\",\"Artist2\",\"pic2\")";
    final String PUT_DATA3 = "insert into " + Tables.MUSIC + " values(\"song3\",\"Album3\",\"Artist3\",\"pic3\")";
    final String PUT_DATA4 = "insert into " + Tables.MUSIC + " values(\"song4\",\"Album4\",\"Artist4\",\"pic4\")";
    final String PUT_DATA5 = "insert into " + Tables.MUSIC + " values(\"song5\",\"Album5\",\"Artist5\",\"pic5\")";

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
