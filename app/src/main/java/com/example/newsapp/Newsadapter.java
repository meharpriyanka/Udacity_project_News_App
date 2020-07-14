package com.example.newsapp;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Newsadapter extends ArrayAdapter<NewsData> {

    public Newsadapter(Context context, ArrayList<NewsData> arrayList) {
        super(context, 0, arrayList);
    }

    public Newsadapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListView = convertView;

        if (currentListView == null) {
            currentListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        NewsData newsData = getItem(position);

        TextView titleView = currentListView.findViewById(R.id.title);
        titleView.setText(newsData.getTitle());

        TextView timeView = currentListView.findViewById(R.id.time);
        timeView.setText(newsData.getTime());



        TextView dateView = currentListView.findViewById(R.id.date);
        dateView.setText(newsData.getDate());


        TextView authorView = currentListView.findViewById(R.id.author);
        authorView.setText("~" + newsData.getAuthor());

        TextView sectionNameView = currentListView.findViewById(R.id.sectionName);
        sectionNameView.setText(newsData.getSectionName());

        return currentListView;


    }
}