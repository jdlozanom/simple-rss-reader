package com.jdlozanom.simplerssreader.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdlozanom.simplerssreader.R;
import com.jdlozanom.simplerssreader.data.NewsItem;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private final LayoutInflater inflater;
    private List<NewsItem> newsList;
    private Context context;

    private final ListItemClickListener itemOnClickListener;

    public NewsListAdapter(Context context, ListItemClickListener itemOnClickListener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.itemOnClickListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_news_list, viewGroup, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        if (newsList != null) {
            //TODO
        }

    }

    @Override
    public int getItemCount() {
        if (newsList != null)
            return newsList.size();
        else return 0;
    }

    public interface ListItemClickListener {
        void onListItemClick(String newsItemId);
    }


    void setNewsList(List<NewsItem> newsItems) {
        newsList = newsItems;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView descriptionTextView, titleTextView;
        private final ImageView previewImage;

        private NewsViewHolder(View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            previewImage = itemView.findViewById(R.id.previewImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            itemOnClickListener.onListItemClick(newsList.get(clickedPosition).getId());
        }
    }


}
