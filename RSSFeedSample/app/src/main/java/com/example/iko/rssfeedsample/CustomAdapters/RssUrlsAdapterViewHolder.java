package com.example.iko.rssfeedsample.CustomAdapters;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iko.rssfeedsample.R;
import com.example.iko.rssfeedsample.database.RssDataHolder;

/**
 * Created by iko on 15.04.16  23:28
 */
public class RssUrlsAdapterViewHolder {

    private ImageView imageView ;
    private TextView textViewName ;
    private TextView textViewDesc ;
    private CheckBox checkBox ;

    RssUrlsAdapterViewHolder( View view){
        this.imageView = (ImageView) view.findViewById(R.id.imageView);
        this.textViewName = (TextView) view.findViewById( R.id.textView_Name );
        this.textViewDesc = (TextView) view.findViewById( R.id.textView_Description );
        this.checkBox = (CheckBox) view.findViewById( R.id.checkBox_Marker );

        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox check = (CheckBox) v;
                RssDataHolder rssHolder = (RssDataHolder) check.getTag(R.string.checkBox_objectTag);
                int position = (int) check.getTag(R.string.checkBox_positionTag);
                rssHolder.getChecked().set(position, check.isChecked());
            }
        });
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

    public void setCheckBoxMarkerValue(boolean state) {
        this.checkBox.setChecked(state);
    }

    public void setCheckBoxTags( RssDataHolder rssDataHolder, int position ) {
        checkBox.setTag(R.string.checkBox_objectTag, rssDataHolder);
        checkBox.setTag(R.string.checkBox_positionTag, position);
    }
}
