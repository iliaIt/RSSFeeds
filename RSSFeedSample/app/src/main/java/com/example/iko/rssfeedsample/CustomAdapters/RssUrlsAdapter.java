package com.example.iko.rssfeedsample.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.iko.rssfeedsample.R;
import com.example.iko.rssfeedsample.database.RssDataHolder;

/**
 * Created by iko on 15.04.16  22:08
 */
public class RssUrlsAdapter extends ArrayAdapter< String >{

    private RssDataHolder rssDataHolder;
    private Context context;

    public RssUrlsAdapter(Context context, RssDataHolder holder){
        super(context, R.layout.rss_urls_list_single_row, R.id.textView_Name,  holder.getUrls().toArray(new String[0]) );
        this.context = context;
        this.rssDataHolder = holder;
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
            row.setTag(R.string.rssAdapter_Feed_DB_Id, this.rssDataHolder.getIds().toArray( new Integer[0])[position] );
        } else {
            holder = (RssUrlsAdapterViewHolder) row.getTag(R.string.rssAdapter_ViewHolderId);
        }

        holder.setImageViewValue(R.drawable.android4);
        holder.setTextViewNameValue(this.rssDataHolder.getUrls().toArray(new String[0])[position]);
        holder.setTextViewDescValue(this.rssDataHolder.getDescriptions().toArray(new String[0])[position]);
        holder.setCheckBoxMarkerValue(this.rssDataHolder.getChecked().toArray(new Boolean[0])[position]);
        holder.setCheckBoxTags(rssDataHolder, position);

        return row;
    }
}
