package com.example.iko.rssfeedsample.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.iko.rssfeedsample.R;

/**
 * Created by iko on 15.04.16  22:08
 */
public class RssUrlsAdapter extends ArrayAdapter< String >{

    String names[];
    String descriptions[];
    int images[];
    Context context;

    public RssUrlsAdapter(Context context, String names[], String descriptions[], int images[]){
        super(context, R.layout.rss_urls_list_single_row, R.id.textView_Name, names);
        this.context = context;
        this.names = names;
        this.descriptions = descriptions;
        this.images = images;
    }

//Optimization by 175%
//findViewById(R.id); is expensive method
// So we will create view holder class
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RssUrlsAdapterViewHolder holder ;
        if( row == null ) {
            //Inflation is expensive method
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.rss_urls_list_single_row, parent, false);
            holder = new RssUrlsAdapterViewHolder(row);
            row.setTag(R.string.rssAdapter_ViewHolderId, holder);
        } else {
            holder = (RssUrlsAdapterViewHolder) row.getTag(R.string.rssAdapter_ViewHolderId);
        }

        holder.setImageViewValue(this.images[position]);
        holder.setTextViewNameValue(names[position]);
        holder.setTextViewDescValue(descriptions[position]);

        return row;
    }
}
