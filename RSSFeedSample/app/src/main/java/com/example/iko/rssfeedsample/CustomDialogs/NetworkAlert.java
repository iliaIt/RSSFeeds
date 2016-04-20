package com.example.iko.rssfeedsample.CustomDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.example.iko.rssfeedsample.R;

/**
 * Created by iko on 29.03.16  22:05
 */
public class NetworkAlert extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_Title).setMessage(R.string.alert_NetworkConnection)
                .setPositiveButton(R.string.button_OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO if it is necessary implement some functionality
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}