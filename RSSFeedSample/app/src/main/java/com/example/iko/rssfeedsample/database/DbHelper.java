package com.example.iko.rssfeedsample.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by iko on 18.04.16  22:39
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rssDB";
    private static final int VERSION = 1;
    protected SQLiteDatabase database;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE rssfeeds ( _id integer primary key autoincrement, feedUrl text NOT NULL, feedDescription text );");
        Log.d("DataBase", "Table \" rssfeeds \" created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO on upgrade
    }

    public void open() throws SQLException{
        database = getWritableDatabase();
    }

    public void close(){
        database.close();
    }

}
