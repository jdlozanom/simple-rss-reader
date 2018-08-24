package com.jdlozanom.simplerssreader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.data.NewsRepository;

import java.util.List;

public class NewsListViewModel extends AndroidViewModel {
    private LiveData<List<NewsItem>> newsList;
    private NewsRepository newsRepository;

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        newsList = newsRepository.getNewsList();
    }

    public LiveData<List<NewsItem>> getNewsList() {
        return newsList;
    }

    public LiveData<List<NewsItem>> getFilteredNewsList(String filterText) {
        return newsRepository.getFilteredNewsList(filterText);
    }
}
