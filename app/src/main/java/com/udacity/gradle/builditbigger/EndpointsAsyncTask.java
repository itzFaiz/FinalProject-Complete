package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rexontechnologies.jokesandroidlib.JokesActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private TaskCompleteListener mTaskCompleteListener;

    public EndpointsAsyncTask(TaskCompleteListener taskCompleteListener) {
        mTaskCompleteListener = taskCompleteListener;
    }

    public EndpointsAsyncTask() {

    }


    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = params[0];

        try {
            return myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return "";
        }


    }

    @Override
    protected void onPostExecute(String string) {
        if (string != null)
            mTaskCompleteListener.onTaskComplete(string);
//        Intent intent = new Intent(context, JokesActivity.class);
//        intent.putExtra(Intent.EXTRA_TEXT, string);
//        context.startActivity(intent);

    }

    public void execute(View.OnClickListener onClickListener) {
    }

    public interface TaskCompleteListener {
        void onTaskComplete(String string);
    }
}
