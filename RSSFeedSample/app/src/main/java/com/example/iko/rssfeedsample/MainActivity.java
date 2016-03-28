package com.example.iko.rssfeedsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button rssFeed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle", "onCreate()");
        setContentView(R.layout.activity_main2);
        rssFeed1 = (Button) findViewById( R.id.buttonRssFeed1);
        rssFeed1.setOnClickListener(this);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
