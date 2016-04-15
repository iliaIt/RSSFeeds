package com.example.iko.rssfeedsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iko.rssfeedsample.CustomThreads.RSSFeedThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    final public String FEED_1 = "http://www.plovdiv24.bg/rss.php";
    private Button rssFeed1;
    private TextView info;
    private TextView rssFeedXML;
    private ListView rssFeedsList;
    String list[] = {"Name1", "name2", "name6", "name6", "name5", "name4", "name3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle", "onCreate()");
        setContentView(R.layout.activity_main);
        rssFeed1 = (Button) findViewById( R.id.buttonRssFeed1);
        rssFeed1.setOnClickListener(this);
        info = (TextView) findViewById(R.id.textView_Info);
        rssFeedXML = (TextView) findViewById(R.id.textView_RssFeedXML);
        rssFeedsList = (ListView) findViewById( R.id.listView_FeedsUrls );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, list );
        rssFeedsList.setAdapter( adapter );
        rssFeedsList.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tview = (TextView) view;
        Toast.makeText(MainActivity.this, tview.getText() , Toast.LENGTH_SHORT).show();
    }
}
