package com.example.miwakapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int resourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int resourceId) {
        super(context, 0, words);
        this.resourceId = resourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWordPosition = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.image);



        TextView miwokText = listItemView.findViewById(R.id.miwok_text_view);
        miwokText.setText(currentWordPosition.getMiwokTranslation());

        TextView defaultText = listItemView.findViewById(R.id.default_text_view);
        defaultText.setText(currentWordPosition.getDefaultTranslation());

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),resourceId);
        textContainer.setBackgroundColor(color);

        if(currentWordPosition.hasImage()) {
            imageView.setImageResource(currentWordPosition.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}