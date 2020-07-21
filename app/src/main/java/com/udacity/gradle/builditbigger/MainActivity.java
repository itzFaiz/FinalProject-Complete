package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rexontechnologies.jokesandroidlib.JokesActivity;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_joke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndpointsAsyncTask(new EndpointsAsyncTask.TaskCompleteListener() {
                    @Override
                    public void onTaskComplete(String result) {
                        Intent intent = new Intent(MainActivity.this, JokesActivity.class);
                        intent.putExtra(JokesActivity.JOKE_KEY, result);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Telling Jokes", Toast.LENGTH_SHORT).show();

                    }
                }).execute(this);
//                Toast.makeText(MainActivity.this, "Showing Jokes", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



//    public void tellJoke(View view) {
//        new EndpointsAsyncTask(new EndpointsAsyncTask.TaskCompleteListener() {
//            @Override
//            public void onTaskComplete(String result) {
//                Intent intent = new Intent(MainActivity.this, JokesActivity.class);
//                intent.putExtra(JokesActivity.JOKE_KEY, result);
//                startActivity(intent);
//            }
//        }).execute(this);
//    }


}
