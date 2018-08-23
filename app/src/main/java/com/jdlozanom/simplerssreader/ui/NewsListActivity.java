package com.jdlozanom.simplerssreader.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jdlozanom.simplerssreader.R;
import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.viewmodel.NewsListViewModel;

import java.util.List;

public class NewsListActivity extends AppCompatActivity {
    private NewsListViewModel newsListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        configureViewModel();
    }

    //Setup ViewModel and observe changes on provided data
    private void configureViewModel() {
        newsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        newsListViewModel.getNewsList().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItemList) {
               //TODO
            }
        });
    }
}
