package com.example.miwakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
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
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        words.add(new Word("father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather));

        final WordAdapter wordAdapter = new WordAdapter(this,words, R.color.category_family);
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
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),item.getAudioResourseId());
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
