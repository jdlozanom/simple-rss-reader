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
import com.jdlozanom.simplerssreader.utils.HtmlUtils;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private final LayoutInflater inflater;
    private final ListItemClickListener itemOnClickListener;
    private List<NewsItem> newsList;
    private Context context;

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
            NewsItem current = newsList.get(i);
            newsViewHolder.descriptionTextView.setText(Html.fromHtml(HtmlUtils.removeImageTags(current.getDescription())));
            newsViewHolder.titleTextView.setText(current.getTitle());

            if (current.getImageUrl() != null) {
                Picasso.with(context).load(current.getImageUrl()).into(newsViewHolder.previewImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (newsList != null)
            return newsList.size();
        else return 0;
    }

    void setNewsList(List<NewsItem> newsItems) {
        newsList = newsItems;
        notifyDataSetChanged();
    }


    public interface ListItemClickListener {
        void onListItemClick(String newsItemId);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
