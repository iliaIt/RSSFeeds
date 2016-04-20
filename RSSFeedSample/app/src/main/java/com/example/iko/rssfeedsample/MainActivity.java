package com.example.iko.rssfeedsample;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iko.rssfeedsample.CustomAdapters.RssUrlsAdapter;
import com.example.iko.rssfeedsample.CustomDialogs.DialogAddRss;
import com.example.iko.rssfeedsample.CustomThreads.RSSFeedThread;
import com.example.iko.rssfeedsample.database.DBConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DialogAddRss.DialogAddRssListener {

    final private String FEED_1 = "http://www.plovdiv24.bg/rss.php";
    final private String PERSIST_RESPONSE_CODE = "RssResponseCode";

    private Button rssFeed1;
    private Button addFeed;
    private Button deleteFeed;

    private TextView info;
    private TextView rssFeedXML;
    private ListView rssFeedsList;

    private DBConnection dataBase;
    //String listValues[] = {"Name1", "name2", "name6", "name6", "name5", "name4", "name3"};

    //resources for adapter
//    String names[];
//    String descriptions[];
//    int images[] = new int[]{ R.drawable.android1, R.drawable.android2, R.drawable.android3, R.drawable.android4, R.drawable.android6, R.drawable.android7 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCycle", "onCreate()");

        dataBase = new DBConnection(getApplicationContext());

        rssFeed1 = (Button) findViewById( R.id.buttonRssFeed1);
        rssFeed1.setOnClickListener(this);
        addFeed = (Button) findViewById(R.id.buttonAddFeed);
        addFeed.setOnClickListener(this);
        deleteFeed = (Button) findViewById(R.id.buttonDeleteFeed);
        deleteFeed.setOnClickListener(this);

        info = (TextView) findViewById(R.id.textView_Info);
        rssFeedXML = (TextView) findViewById(R.id.textView_RssFeedXML);
        rssFeedsList = (ListView) findViewById( R.id.listView_FeedsUrls );

//        Resources resources = getResources();
//        names = resources.getStringArray(R.array.names);
//        descriptions = resources.getStringArray(R.array.description);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, listValues );
        RssUrlsAdapter adapter = new RssUrlsAdapter( this, dataBase.selectAllFromRssFeeds() );
        rssFeedsList.setAdapter(adapter);
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
        loadPersistentSimpleData();

    }

    @Override
    protected void onPause() {  //back to onResume
        super.onPause();
        Log.d("LifeCycle", "onPause()");
        savePersistentSimpleData();
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
        dataBase.close();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonRssFeed1:{
                Log.d( "Click Listener", "Button RssFeed1 was clicked ! " );
                RSSFeedThread feedThread = new RSSFeedThread( this, rssFeedXML, info);
                feedThread.execute(FEED_1);
                break;
            }
            case R.id.buttonAddFeed:{
                DialogFragment dialog = new DialogAddRss();
                dialog.show(getFragmentManager(), "AddRss");
                break;
            }
            case R.id.buttonDeleteFeed:{
                //TODO ...
                for( int i=0; i< rssFeedsList.getCount(); i++){
                    View row = rssFeedsList.getChildAt(i);
                    rssFeedsList.removeViewAt(i);
                }
                break;
            }
            default: Log.d("Default", "Default");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tview = (TextView) view.findViewById( R.id.textView_Name);
        Toast.makeText(MainActivity.this, tview.getText() , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == 1 ){
            Toast.makeText(MainActivity.this, "Intent result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveClicked(String url, String description) {
        dataBase.insertNewRssFeed(url, description);
        RssUrlsAdapter adapter = new RssUrlsAdapter( this, dataBase.selectAllFromRssFeeds() );
        rssFeedsList.setAdapter(adapter);
        rssFeedsList.invalidate();
    }

    //Shared preference
    public void savePersistentSimpleData(){
        String infoText = info.getText().toString();
        SharedPreferences preferences = getPreferences(MODE_PRIVATE); //preferences = getSharedPreferences("", MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PERSIST_RESPONSE_CODE, infoText );
        editor.commit();
    }
    public void loadPersistentSimpleData(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE); //preferences = getSharedPreferences("", MODE_PRIVATE );
        String infoText = preferences.getString(PERSIST_RESPONSE_CODE, "");
        if( !infoText.isEmpty() ){
            info.setText(infoText);
        }
    }

    public void testIntents(){
        Intent intent = new Intent(this, TestActivity.class );
        intent.putExtra("testData1", "data1");
        //startActivity(intent);
        //The request code is the identifier for the request intent to know which request gets response in the onActivityResult
        startActivityForResult(intent, 1);

    }

}
