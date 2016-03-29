package com.example.iko.rssfeedsample.Threads;

import android.app.DialogFragment;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.iko.rssfeedsample.AlertDialogs.CustomAlerts;
import com.example.iko.rssfeedsample.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by iko on 10.03.16.
 */
public class RSSFeedThread extends AsyncTask< String, String, String > {

    private TextView xmlResponse;
    private TextView info;
    private int statusCode;
    private MainActivity activity;

    public RSSFeedThread( MainActivity activity, TextView textView, TextView info ){
        this.xmlResponse = textView;
        this.info = info;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.d("LifeCycle", "onPreExecute");
    }

    @Override
    protected String doInBackground( String ... params ) {
        //Send the rss request
        String response = feed( params[0] );
        publishProgress("Update progress"); //Call the onProgressUpdate method
        return response;
    }

    @Override
    protected void onProgressUpdate( String... values ){
        super.onProgressUpdate(values);
        Log.d("LifeCycle", values[0]);
    }

    @Override
    protected void onPostExecute( String strings ) {
        super.onPostExecute(strings);
        Log.d("LifeCycle", "onPostExecute");
        //Here I have the xml feed in the string, and I can convert it now
        if( strings == null || strings.isEmpty() ){
            DialogFragment newFragment = new CustomAlerts();
            newFragment.show(activity.getFragmentManager(), "test");

        }
        xmlResponse.setText( strings );
        info.setText( "Info: Status code - " + statusCode );
    }

    protected String feed( String urlFeed ){

        StringBuilder response = new StringBuilder();

        try {
            //Way to encode the URL-string  String stringToReverse = URLEncoder.encode(args[1], "UTF-8");
            URI uri = new URI( urlFeed );
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // give it 15 seconds to respond
            // connection.setReadTimeout(15*1000);
            connection.setDoInput(true);
            connection.setRequestProperty("accept-charset", "UTF-8" );
            connection.connect();
            statusCode = connection.getResponseCode();

            BufferedReader inReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251"));

            String responseLine;
            while ( ( responseLine = inReader.readLine() ) != null ) {
                response.append( responseLine + "\n" );
            }


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
