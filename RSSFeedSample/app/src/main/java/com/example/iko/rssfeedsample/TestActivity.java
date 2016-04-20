package com.example.iko.rssfeedsample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class TestActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //setListAdapter();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(TestActivity.this, "test", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String data = getIntent().getStringExtra("testData1");
        //to return an result to activity that call this intent
        Intent intent = new Intent();
        intent.putExtra("Response", "DataBack");
        setResult(RESULT_OK, intent);
        finish();

    }
}
