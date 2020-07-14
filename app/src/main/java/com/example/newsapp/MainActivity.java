package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsData>> {
    private Newsadapter newsAdapter;
    private static final String REQUEST_URL = "https://content.guardianapis.com/search";
    private TextView emptyTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyTextView = findViewById(R.id.empty_text);
        progressBar = findViewById(R.id.progress_bar);


        ConnectivityManager CM = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        newsAdapter = new Newsadapter(this, new ArrayList<NewsData>());
        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(emptyTextView);

        ListAdapter newsadapter;
        listView.setAdapter(newsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                NewsData current = newsAdapter.getItem(position);

                Uri news_Uri = Uri.parse(current.getWebURL());

                Intent website = new Intent(Intent.ACTION_VIEW, news_Uri);
                startActivity(website);
            }
        });

        NetworkInfo NI = CM.getActiveNetworkInfo();
        if (NI != null && NI.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("There is no internet Connection Please try Again");
        }



    }


    @Override
    public Loader<List<NewsData>> onCreateLoader(int i, Bundle bundle) {
        Uri base = Uri.parse(REQUEST_URL);

        Uri.Builder uriBuilder = base.buildUpon();

        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "test");

        return new NewsLoad(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> tunder) {

        newsAdapter.clear();
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText("No news found!\\");


        if (tunder != null && !tunder.isEmpty()) {
            newsAdapter.addAll((Collection<? extends NewsData>) tunder);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {

        newsAdapter.clear();
    }}

