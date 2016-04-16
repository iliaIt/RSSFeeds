package com.example.iko.rssfeedsample;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
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
import com.example.iko.rssfeedsample.CustomThreads.RSSFeedThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    final private String FEED_1 = "http://www.plovdiv24.bg/rss.php";
    final private String PERSIST_RESPONSE_CODE = "RssResponseCode";
    private Button rssFeed1;
    private TextView info;
    private TextView rssFeedXML;
    private ListView rssFeedsList;
    //String listValues[] = {"Name1", "name2", "name6", "name6", "name5", "name4", "name3"};

    //resources for adapter
    String names[];
    String descriptions[];
    int images[] = new int[]{ R.drawable.android1, R.drawable.android2, R.drawable.android3, R.drawable.android4, R.drawable.android6, R.drawable.android7 };

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

        Resources resources = getResources();
        names = resources.getStringArray(R.array.names);
        descriptions = resources.getStringArray(R.array.description);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, listValues );
        RssUrlsAdapter adapter = new RssUrlsAdapter( this, names, descriptions, images);
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
        TextView tview = (TextView) view.findViewById( R.id.textView_Name);
        Toast.makeText(MainActivity.this, tview.getText() , Toast.LENGTH_SHORT).show();
    }

    //Shared preference
    public void savePersistentSimpleData(){
        String infoText = info.getText().toString();
        SharedPreferences preferences = getPreferences( MODE_PRIVATE ); //preferences = getSharedPreferences("", MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PERSIST_RESPONSE_CODE, infoText );
        editor.commit();
    }
    public void loadPersistentSimpleData(){
        SharedPreferences preferences = getPreferences( MODE_PRIVATE ); //preferences = getSharedPreferences("", MODE_PRIVATE );
        String infoText = preferences.getString(PERSIST_RESPONSE_CODE, "");
        if( !infoText.isEmpty() ){
            info.setText(infoText );
        }
    }

}
