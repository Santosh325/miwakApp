package com.example.miwakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.example.miwakapp.R.drawable.*;
import static java.lang.String.*;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS ) {
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Word> words = new ArrayList<Word>();

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        words.add(new Word("one", "lutti", number_one,R.raw.number_one));
        words.add(new Word("two", "otiiko", number_two,R.raw.number_two));
        words.add(new Word("three", "tolookosu", number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa", number_four,R.raw.number_four));
        words.add(new Word("five", "massokka", number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka", number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku", number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta", number_eight,R.raw.number_eight));
        words.add(new Word("nine", "wo’e", number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", number_ten,R.raw.number_ten));



        final WordAdapter wordAdapter = new WordAdapter(this,words,R.color.category_numbers);
        ListView listView = findViewById(R.id.list);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = words.get(position);
                releaseMediaPlayer();


                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this,item.getAudioResourseId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }


            }
        });


    }
     public void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
     }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
