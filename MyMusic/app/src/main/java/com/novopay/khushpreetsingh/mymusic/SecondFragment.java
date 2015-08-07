package com.novopay.khushpreetsingh.mymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.novopay.khushpreetsingh.mymusic.Models.MusicApiResponse;
import com.novopay.khushpreetsingh.mymusic.network.MusicAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class SecondFragment extends Fragment {

    private GridView gridView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;
    private MusicApiResponse musicResponse;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        gridView = (GridView)  view.findViewById(R.id.fragment_second_gridview);


        MusicAPI.getApi().getMusicList(new Callback<MusicApiResponse>() {

            @Override
            @DebugLog
            public void success(MusicApiResponse musicApiResponse, Response response) {

                musicAdapter = new MusicAdapter(getActivity(), musicApiResponse.getResults().getCollection1());

                gridView.setAdapter(musicAdapter);
                musicResponse = musicApiResponse;

            }

            @Override
            @DebugLog
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "some error" + error.getResponse().getReason(), Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MusicServices.class);
                intent.putExtra(MusicServices.KEY_METHOD, MusicServices.METHOD_PLAY);
                getActivity().startService(intent);
                Intent intentActivity = new Intent(getActivity(), MusicActivity.class);
                intentActivity.putExtra("MUSICLIST", musicResponse);
                intentActivity.putExtra("POS", position);
                getActivity().startActivity(intentActivity);

            }
        });
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("0SongName", "0Album", "0Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("1SongName", "1Album", "1Artist","http://loremflickr.com/320/240"));
        musicList.add(new Music("2SongName", "2Album", "1Artist", "http://loremflickr.com/320/240"));
        musicList.add(new Music("3SongName", "3Album", "3Artist", "http://loremflickr.com/320/240"));
        musicList.add(new Music("4SongName", "4Album", "4Artist", "http://loremflickr.com/320/240"));

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
