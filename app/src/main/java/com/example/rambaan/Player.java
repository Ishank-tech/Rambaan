package com.example.rambaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity {

    private Button btnPlay, btnNext, btnPrev, btnFastForward, btnFastRewind;
    private TextView txtSongName, txtSongStart,  txtSongEnd;
    private SeekBar seekBar;
//    private BarVisualizer barVisualizer;

    private ImageView imageView;

    private String songName;
    public static final String EXTRA_NAME = "song_name";

    static MediaPlayer mediaPlayer;
    int position;
    ArrayList<File> mySongs;

    private Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnPlay = findViewById(R.id.btnPlay);
        btnFastForward = findViewById(R.id.btnFastForward);
        btnFastRewind = findViewById(R.id.btnRewind);

        txtSongName = findViewById(R.id.txtSong);
        txtSongStart = findViewById(R.id.txtSongStart);
        txtSongEnd = findViewById(R.id.txtSongEnd);
        seekBar = findViewById(R.id.seekbar);
//        barVisualizer = findViewById(R.id.wave);

        imageView = findViewById(R.id.imgView);

        if(mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.release();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
        String sName = intent.getStringExtra("songName");
        position = bundle.getInt("pos",0);
        txtSongName.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(position).toString());
        songName = mySongs.get(position).getName();
        txtSongName.setText(songName);

        mediaPlayer = MediaPlayer.create(Player.this, uri);
        mediaPlayer.start();

//        Bundle bundle = this.getArguments();
//        if(bundle != null){
//            mySongs = (ArrayList<File>) bundle.getSerializable("songs");
//            String sName = bundle.getString("songName");
//            position = bundle.getInt("pos",0);
//            txtSongName.setSelected(true);
//            Uri uri = Uri.parse(mySongs.get(position).toString());
//            songName = mySongs.get(position).getName();
//            txtSongName.setText(songName);
//
//            mediaPlayer = MediaPlayer.create(Player.this, uri);
//            mediaPlayer.start();
//
//        }

        updateSeekbar = new Thread(){
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPos = 0;
                while (currentPos < totalDuration){
                    try {
                        sleep(500);
                        currentPos = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPos);
                    }catch (InterruptedException | IllegalStateException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        seekBar.setMax(mediaPlayer.getDuration());
        updateSeekbar.start();
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.purple_700), PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.purple_700),PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        txtSongEnd.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                txtSongStart.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        },delay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    btnPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow);
                    mediaPlayer.pause();
                }else{
                    btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause);
                    mediaPlayer.start();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnNext.performClick();
            }
        });

//        int audioSessionId = mediaPlayer.getAudioSessionId();
//        if(audioSessionId != -1){
//            barVisualizer.setAudioSessionId(audioSessionId);
//        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position+1)%mySongs.size());
                Uri uri = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(Player.this, uri);
                songName = mySongs.get(position).getName();
                txtSongName.setText(songName);
                mediaPlayer.start();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position-1)<0)?(mySongs.size()-1):position-1;
                Uri uri = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(Player.this, uri);
                songName = mySongs.get(position).getName();
                txtSongName.setText(songName);
                mediaPlayer.start();
            }
        });

        btnFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                }
            }
        });

        btnFastRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
                }
            }
        });

    }

    public String createTime(int duration){
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        time = time + min + ":";
        if(sec<10){
            time+="0";
        }
        time+=sec;
        return time;
    }

}