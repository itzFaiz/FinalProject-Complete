package com.udacity.gradle.builditbigger;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    private ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void jokeTest() {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(activityActivityTestRule.getActivity());
        try {
            String joke = endpointsAsyncTask.get();
            assertNotNull(joke);
            assertTrue("", joke.length() > 0);
        } catch (Exception e) {
            Log.d("TEST", e.getMessage());
        }

    }
}