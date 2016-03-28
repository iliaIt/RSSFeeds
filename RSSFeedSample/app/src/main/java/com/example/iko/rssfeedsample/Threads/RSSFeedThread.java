package com.example.iko.rssfeedsample.Threads;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by iko on 10.03.16.
 */
public class RSSFeedThread extends AsyncTask< List<String>, String, List<String> > {

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<String> doInBackground(List<String>... params) {
        //Send the rss request

        publishProgress("Update progress"); //Call the onProgressUpdate method
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
    }

}
