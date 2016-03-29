package com.example.iko.rssfeedsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iko.rssfeedsample.CustomThreads.RSSFeedThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final public String FEED_1 = "http://www.plovdiv24.bg/rss.php";
    private Button rssFeed1;
    private TextView info;
    private TextView rssFeedXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle", "onCreate()");
        setContentView(R.layout.activity_main);
        rssFeed1 = (Button) findViewById( R.id.buttonRssFeed1);
        rssFeed1.setOnClickListener(this);
        info = (TextView) findViewById(R.id.textView_Info);
        rssFeedXML = (TextView) findViewById(R.id.textView_RssFeedXML);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle", "onResume()");
    }

    @Override
    protected void onPause() {  //back to onResume
        super.onPause();
        Log.d("LifeCycle", "onPause()");
    }

    @Override
    protected void onStop() {   //back to onRestart then onStart
        super.onStop();
        Log.d("LifeCycle", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "onDestroy()");
    }

    @Override
    public void onClick(View v) {

        if( v.getId() == R.id.buttonRssFeed1 ){
            Log.d( "Click Listener", "Button RssFeed1 was clicked ! " );
        }
        RSSFeedThread feedThread = new RSSFeedThread( this, rssFeedXML, info);
        feedThread.execute(FEED_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
