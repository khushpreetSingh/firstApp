package com.novopay.khushpreetsingh.mymusic;

import android.content.Intent;
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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import hugo.weaving.DebugLog;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class FirstFragment extends Fragment {

    private ListView listView;
    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;

    @Override
    @DebugLog
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("0SongName", "0Album", "0Artist","http://loremflickr.com/320/240" ));
        musicList.add(new Music("1SongName", "1Album", "1Artist","http://loremflickr.com/320/240" ));
        musicList.add(new Music("2SongName", "2Album", "1Artist","http://loremflickr.com/320/240" ));
        musicList.add(new Music("3SongName", "3Album", "3Artist","http://loremflickr.com/320/240" ));
        musicList.add(new Music("4SongName", "4Album", "4Artist","http://loremflickr.com/320/240" ));



    }

    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first_listview);
        musicAdapter = new MusicAdapter(getActivity(), musicList);
        listView.setAdapter(musicAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),MusicActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    @DebugLog
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
