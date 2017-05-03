package com.example.android.thegunnersnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.thegunnersnews.R;
import com.example.android.thegunnersnews.model.Result;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yogesh on 10-04-2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static List<Result> newsList;
    private int rowLayout;
    private Context context;
    private Result currentResult;

    public NewsAdapter(List<Result> news, int rowLayout, Context context) {
        this.newsList = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public static List<Result> getNewsList() {
        return newsList;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, final int position) {
        currentResult = newsList.get(position);

        String dT = currentResult.getWebPublicationDate();
        String title = currentResult.getWebTitle();
        String titleToDisplay = "";

        if (title.contains(":")) {
            int indexOfColon = title.indexOf(':');
            titleToDisplay = title.substring(indexOfColon + 1, title.length());
        } else {
            titleToDisplay = title;
        }

        String thumbnail = currentResult.getFields().getThumbnail();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date d1;
        String dateString = "";
        String dateOnly = "";
        String yearOnly = "";
        try {
            d1 = dFormat.parse(dT);
            dateString = d1.toString();
            dateOnly = dateString.substring(4, 10);
            yearOnly = dateString.substring(dateString.length() - 4, dateString.length());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.newsTitle.setText(titleToDisplay);
        holder.newsDate.setText(dateOnly + "\n" + yearOnly);
        Picasso.with(context).load(thumbnail).into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        private final Context mContext;
        private TextView newsTitle;
        private TextView newsDate;
        private ImageView newsImage;

        public NewsViewHolder(View v) {
            super(v);
            newsTitle = (TextView) v.findViewById(R.id.newsTitleId);
            newsDate = (TextView) v.findViewById(R.id.dateId);
            v.setOnClickListener(this);
            mContext = v.getContext();
            newsImage = (ImageView) v.findViewById(R.id.imageview);
        }

        @Override
        public void onClick(View v) {
            Uri newsUri = Uri.parse(NewsAdapter.getNewsList().get(getAdapterPosition()).getWebUrl());
            Log.d("URII", "uri " + newsUri);
            Log.d("CLICKEDD", "clicked " + getAdapterPosition());
            Intent i = new Intent(Intent.ACTION_VIEW, newsUri);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }
}
