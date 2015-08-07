package com.novopay.khushpreetsingh.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.novopay.khushpreetsingh.mymusic.Models.Collection1;
import com.novopay.khushpreetsingh.mymusic.Models.MusicApiResponse;
import com.novopay.khushpreetsingh.mymusic.network.MusicAPI;
import com.novopay.khushpreetsingh.mymusic.provider.MusicSQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Events.UpdateMusicBar;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private ListView listView;
    private List<Collection1> musicList = new ArrayList<>();
    private MusicApiResponse musicResponse;
    MusicCursor musicCursor;
    MusicSQLiteOpenHelper musicdbHelper;
    SQLiteDatabase musicdbr, musicdbw;


    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first_listview);


        //Thread part
       /* Runnable myrunnable = new Runnable() {
            @Override
            public void run() {
                MusicAPI.getApi().getMusicList(new Callback<MusicApiResponse>() {
                    @Override
                    public void success(MusicApiResponse musicApiResponse, Response response) {
                        Toast.makeText(getActivity(),"this is in the thread",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        };
        Thread thread = new Thread(myrunnable);
        thread.start();*/

        new MusicAsyncTask().execute();
        musicdbHelper = new MusicSQLiteOpenHelper(getActivity());

        musicdbr = musicdbHelper.getReadableDatabase();
        final Cursor cursor = musicdbr.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);
        musicCursor = new MusicCursor(getActivity(),cursor);
        listView.setAdapter(musicCursor);

        /*

        MusicAPI.getApi().getMusicList(new Callback<MusicApiResponse>() {
            @Override
            public void success(MusicApiResponse musicApiResponse, Response response) {
                int i = 0;
                musicdbw = musicdbHelper.getWritableDatabase();
                while(i<100) {
                    String s,a,im;
                    s = musicApiResponse.getResults().getCollection1().get(i).getSongName().getText();
                    a = musicApiResponse.getResults().getCollection1().get(i).getArtistName().getText();
                    im = musicApiResponse.getResults().getCollection1().get(i).getImageUrl().getSrc();
                    Toast.makeText(getActivity(),MusicSQLiteOpenHelper.Tables.MUSIC,Toast.LENGTH_LONG).show();
                    //musicdbw.execSQL("insert into "+ MusicSQLiteOpenHelper.Tables.MUSIC +" (\"imageurl\",\"album\",\"song\") values("+im+","+a+","+s+")");
                    i = i+1;
                }
                musicdbHelper = new MusicSQLiteOpenHelper(getActivity());
                musicdbr = musicdbHelper.getReadableDatabase();
                cursor = musicdbr.query(MusicSQLiteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);
                musicCursor = new MusicCursor(getActivity(), cursor);
                listView.setAdapter(musicCursor);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

         */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String song = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.SONG));
                String album = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.ALBUM));
                String image = cursor.getString(cursor.getColumnIndex(MusicSQLiteOpenHelper.TableMusic.IMAGE_URL));
                Intent intent = new Intent(getActivity(), MusicServices.class);
                intent.putExtra(MusicServices.KEY_METHOD, MusicServices.METHOD_PLAY);
                intent.putExtra("SONG",song);
                intent.putExtra("ALBUM",album);
                intent.putExtra("IMAGE",image);
                //getActivity().startService(intent);
                EventBus.getDefault().post(new UpdateMusicBar(song,album,image));


            }
        });
        return view;
    }

    @Override
    @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class MusicAsyncTask extends AsyncTask<Void, Void, MusicApiResponse>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected MusicApiResponse doInBackground(Void... params) {
            return MusicAPI.getApi().getMusicList();
        }

        @Override
        protected void onPostExecute(MusicApiResponse aVoid) {
            MusicAdapter musicAdapter = new MusicAdapter(getActivity(),aVoid.getResults().getCollection1());
            listView.setAdapter(musicAdapter);
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
