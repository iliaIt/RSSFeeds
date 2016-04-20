package com.example.iko.rssfeedsample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iko on 18.04.16  23:05
 */
public class DBConnection extends DbHelper {

    private RssDataHolder rssData;

    public DBConnection(Context context) {
        super(context);
        open();
    }

    public void insertNewRssFeed( String url, String description ){
        //TODO implement some text template with regular expression
        ContentValues cValues = new ContentValues();
        cValues.put("feedUrl", url);
        cValues.put("feedDescription", description);
        this.database.insert("rssfeeds", null, cValues);
        Log.d("DataBase", "New rss feed is inserted !");
    }

    public RssDataHolder selectAllFromRssFeeds(){
        rssData = new RssDataHolder();
        Cursor cursor = this.database.query("rssfeeds",new String[]{"_id", "feedUrl", "feedDescription"}, null, null, null, null, null );

        if( cursor.moveToFirst() ){
            do{
                rssData.addToIds( cursor.getInt(cursor.getColumnIndex("_id")) );
                rssData.addToUrls(cursor.getString(cursor.getColumnIndex("feedUrl")));
                rssData.addToDescription( cursor.getString(cursor.getColumnIndex("feedDescription")) );
                rssData.addToChecked( false );
            }while(cursor.moveToNext());
        }
        cursor.close();
        return rssData;
    }

}
