package com.example.iko.rssfeedsample.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iko on 20.04.16  17:20
 */
public class RssDataHolder {

    private List<Integer> ids;
    private List<String> urls;
    private List<String> descriptions;
    private List<Boolean> checked;

    public RssDataHolder() {
        this.ids = new ArrayList<Integer>();
        this.urls = new ArrayList<String>();
        this.descriptions = new ArrayList<String>();
        this.checked = new ArrayList<Boolean>();
    }

    public void addToIds(int id){
        this.ids.add(id);
    }

    public void addToUrls(String url){
        this.urls.add(url);
    }

    public void addToDescription(String description){
        this.descriptions.add(description);
    }

    public void addToChecked(boolean state){
        this.checked.add(state);
    }

    public List<Integer> getIds() {
        return ids;
    }

    public List<String> getUrls() {
        return urls;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<Boolean> getChecked() {
        return checked;
    }
}
