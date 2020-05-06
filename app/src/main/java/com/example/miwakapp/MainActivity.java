package com.example.miwakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView numbers,phrases,colors,family;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = findViewById(R.id.numbers);
        phrases = findViewById(R.id.phrases);
        colors = findViewById(R.id.colors);
        family = findViewById(R.id.family);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(intent);
            }
        });
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(intent);
            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(intent);
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(intent);
            }
        });
    }
}
