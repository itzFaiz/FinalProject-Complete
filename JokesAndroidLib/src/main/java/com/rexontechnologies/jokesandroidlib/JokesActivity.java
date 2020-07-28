package com.rexontechnologies.jokesandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokesActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "jokes";
    TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setContentView(R.layout.activity_jokes);
        jokeText = (TextView) findViewById(R.id.joke_text_view);
        Intent i = getIntent();
        if (i.hasExtra(JOKE_KEY)) {
            String key = i.getStringExtra(JOKE_KEY);
            jokeText.setText(key);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //Back Button to navigate back to the details screen
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
