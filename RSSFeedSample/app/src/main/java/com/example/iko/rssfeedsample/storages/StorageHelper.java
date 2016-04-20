package com.example.iko.rssfeedsample.storages;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * Created by iko on 19.04.16  11:59
 */

// It is just testing class for now
public class StorageHelper {

    public static void internalStorageTest( Activity activity) throws FileNotFoundException {
        //Work with internal storage
        File file = new File( activity.getFilesDir(), "textTest");
        OutputStream stream = activity.openFileOutput("textTest", Context.MODE_PRIVATE);
        //work with external storage
        File file1 = Environment.getExternalStorageDirectory();
        File file2 = Environment.getExternalStoragePublicDirectory("");
        File file3 = activity.getExternalFilesDir("");
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
