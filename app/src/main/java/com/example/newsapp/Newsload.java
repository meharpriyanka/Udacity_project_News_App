package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class NewsLoad extends AsyncTaskLoader<List<NewsData>> {

    private String mURL;

    public NewsLoad(Context context, String url) {
        super(context);
        mURL = url;
    }

    public NewsLoad(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        List<NewsData> newsDataList = com.example.newsapp.QueryUtils.fetchNewsData(mURL);
        return newsDataList;
    }
}