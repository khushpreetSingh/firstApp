package com.novopay.khushpreetsingh.mymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import Events.MusicPlayEvent;
import Events.UpdateMusicBar;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class listOfMusicActivity extends FragmentActivity {
    private ViewPager viewPager;
    private final int NUMBER_OF_PAGES = 2;
    private MusicListFragmentStatePagerAdapter musicListFragmentStatePagerAdapter;

    public RelativeLayout music_bar;
    Button music_bar_play;
    Button music_bar_pause;
    TextView music_bar_song_name;
    TextView music_bar_song_author;


    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpage);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.first_fragment, viewPager, false);
        viewPager = (ViewPager) findViewById(R.id.activity_viewpager);
        musicListFragmentStatePagerAdapter = new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicListFragmentStatePagerAdapter);

        music_bar=(RelativeLayout) findViewById(R.id.music_bar);
        music_bar_song_name=(TextView) findViewById(R.id.music_bar_song_name);
        music_bar_song_author=(TextView)findViewById(R.id.music_bar_song_author);
        music_bar_play=(Button) findViewById(R.id.music_bar_play);
        music_bar_pause=(Button) findViewById(R.id.music_bar_pause);
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }
    }


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    public void onEvent(UpdateMusicBar event)
    {
        music_bar.setVisibility(View.VISIBLE);
        music_bar_song_name.setText(event.songname);
        music_bar_song_author.setText(event.songalbum);
        //Toast.makeText(this,"This is an Event "+ event.songalbum,Toast.LENGTH_LONG).show()();
        Intent intent=new Intent(listOfMusicActivity.this, MusicServices.class);
        intent.putExtra(MusicServices.KEY_METHOD, MusicServices.METHOD_PLAY);
        startService(intent);
    }
    public void onEvent(MusicPlayEvent event) {
        music_bar_play.setVisibility(View.INVISIBLE);
        music_bar_pause.setVisibility(View.VISIBLE);
        music_bar_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_bar_play.setVisibility(View.INVISIBLE);
                music_bar_pause.setVisibility(View.VISIBLE);
                MusicServices.startMusic();

            }
        });
        music_bar_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_bar_pause.setVisibility(View.INVISIBLE);
                music_bar_play.setVisibility(View.VISIBLE);
                MusicServices.pauseMusic();

            }
        });
        music_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(listOfMusicActivity.this, MusicActivity.class));
            }
        });
    }
}
