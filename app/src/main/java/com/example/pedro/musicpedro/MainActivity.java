package com.example.pedro.musicpedro;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    Button play,pause,next;
    MediaPlayer sound;
    Integer currentSong;
    private ArrayList<Integer> songs;
    private ArrayList<Integer> songLyrics;
    private ArrayList<Integer> songTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        next = (Button) findViewById(R.id.next);

        songs = new ArrayList<>();
        songs.add(R.raw.dear);
        songs.add(R.raw.inloving);
        songs.add(R.raw.nirvana);
        songs.add(R.raw.upside);
        songs.add(R.raw.unforgiven);

        songLyrics = new ArrayList<>();
        songLyrics.add(R.string.a7x);
        songLyrics.add(R.string.inloving);
        songLyrics.add(R.string.nirvana);
        songLyrics.add(R.string.upside);
        songLyrics.add(R.string.unforgiven);

        songTitles = new ArrayList<>();
        songTitles.add(R.string.a7x_title);
        songTitles.add(R.string.inloving_title);
        songTitles.add(R.string.nirvana_title);
        songTitles.add(R.string.upside_title);
        songTitles.add(R.string.unforgiven_title);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSong == null) {
                    currentSong = 0;
                }


                sound = MediaPlayer.create(MainActivity.this, songs.get(currentSong));
                stopMusic();
                playMusic(getString(songTitles.get(currentSong)), songLyrics.get(currentSong));
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });
    }

    public void nextSong(View view) {
        if (currentSong == null) {
            currentSong = -1;
        } else {
            stopMusic();
        }

        if (currentSong < songs.size() -1) {
            currentSong++;
            sound = MediaPlayer.create(MainActivity.this, songs.get(currentSong));
            playMusic(getString(songTitles.get(currentSong)), songLyrics.get(currentSong));
        } else {
            currentSong = 0;
            sound = MediaPlayer.create(MainActivity.this, songs.get(currentSong));
            playMusic(getString(songTitles.get(currentSong)), songLyrics.get(currentSong));

        }
    }

    private void stopMusic() {
        if (sound.isPlaying()){
            sound.pause();
        }
    }

    private void playMusic(String title, int lyrics) {
        TextView textView = (TextView) findViewById(R.id.welcome);
        textView.setText(title);

        TextView lyricsTextView = (TextView) findViewById(R.id.lyrics);
        lyricsTextView.setText(getString(lyrics));
        sound.start();
    }


}
