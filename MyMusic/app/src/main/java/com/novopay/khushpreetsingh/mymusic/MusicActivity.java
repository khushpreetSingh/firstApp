package com.novopay.khushpreetsingh.mymusic;

import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.logging.Handler;


public class MusicActivity extends ActionBarActivity {

    private Button mPlayButton;
    private Button mPauseButton;
    private Button mFastForward;
    private Button mRewind;
    private MediaPlayer mediaPlayer;
    private SeekBar mSeek;
    public static int MESSAGE_SLEEP =11;
    public static int MESSAGE_WAKE_UP=10;

    MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mPlayButton = (Button) findViewById(R.id.activity_main_play);
        mPauseButton = (Button) findViewById(R.id.activity_main_pause);
        mFastForward = (Button) findViewById(R.id.activity_main_fastforward);
        mRewind = (Button) findViewById(R.id.activity_main_rewind);
        mSeek = (SeekBar) findViewById(R.id.activity_main_seek);

        mediaPlayer = MediaPlayer.create(this, R.raw.a);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP);
                //Toast.makeText(MusicActivity.this, "Play is Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP);

                //Toast.makeText(MusicActivity.this, "Pause is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
            }
        });

        mRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
            }
        });

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(MusicActivity.this, "Song is Complete", Toast.LENGTH_SHORT).show();
        }
    });

    mSeek.setMax(mediaPlayer.getDuration());
    mSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser)
                mediaPlayer.seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    class MusicHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mSeek.setProgress(mediaPlayer.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP, 200);
                    }
                }
            }
            super.handleMessage(msg);
        }
    }
}
