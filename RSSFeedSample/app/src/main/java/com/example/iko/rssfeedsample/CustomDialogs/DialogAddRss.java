package com.example.iko.rssfeedsample.CustomDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.iko.rssfeedsample.R;

/**
 * Created by iko on 20.04.16  16:00
 */
public class DialogAddRss extends DialogFragment {

    EditText url;
    EditText description;
    DialogAddRssListener activityNotifier;

    public interface DialogAddRssListener{
        public void onSaveClicked(String url, String description);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_rss_feed, null, false);
        url = (EditText) view.findViewById(R.id.editText_DialogAddRSS_rssUrl);
        description = (EditText) view.findViewById(R.id.editText_DialogAddRSS_rssDescription);

        builder.setView(view)
                .setPositiveButton(R.string.button_Save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        activityNotifier.onSaveClicked(url.getText().toString(), description.getText().toString() );
                    }
                })
                .setNegativeButton(R.string.button_Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        DialogAddRss.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityNotifier = (DialogAddRssListener) activity;
    }
}