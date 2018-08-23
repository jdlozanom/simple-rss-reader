package com.jdlozanom.simplerssreader.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jdlozanom.simplerssreader.R;
import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.viewmodel.NewsListViewModel;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements NewsListAdapter.ListItemClickListener {
    private NewsListViewModel newsListViewModel;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        RecyclerView recyclerView = findViewById(R.id.news_list_recyclerview);
        adapter = new NewsListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        configureViewModel();
    }

    //Setup ViewModel and observe changes on provided data to notify to the list adapter
    private void configureViewModel() {
        newsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        newsListViewModel.getNewsList().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItemList) {
                adapter.setNewsList(newsItemList);
            }
        });
    }

    @Override
    public void onListItemClick(String newsItemId) {
        Intent intent = new Intent(NewsListActivity.this, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, newsItemId);
        startActivity(intent);
    }
}
