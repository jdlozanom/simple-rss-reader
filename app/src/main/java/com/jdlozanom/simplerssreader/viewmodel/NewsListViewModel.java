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

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        NewsRepository newsRepository = new NewsRepository(application);
        newsList = newsRepository.getNewsList();
    }

    public LiveData<List<NewsItem>> getNewsList() {
        return newsList;
    }

}
