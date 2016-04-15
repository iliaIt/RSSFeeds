package com.example.iko.rssfeedsample.CustomAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iko.rssfeedsample.R;

/**
 * Created by iko on 15.04.16  23:28
 */
public class RssUrlsAdapterViewHolder {

    private ImageView imageView ;
    private TextView textViewName ;
    private TextView textViewDesc ;

    RssUrlsAdapterViewHolder( View view){
        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        this.textViewName = (TextView) view.findViewById( R.id.textView_Name );
        this.textViewDesc = (TextView) view.findViewById( R.id.textView_Description );

    }

    public void setImageViewValue(int imageView) {
        this.imageView.setImageResource(imageView);
    }

    public void setTextViewNameValue(String name) {
        this.textViewName.setText(name);
    }

    public void setTextViewDescValue(String description) {
        this.textViewDesc.setText(description);
    }
}
