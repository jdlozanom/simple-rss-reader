package com.jdlozanom.simplerssreader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.data.NewsRepository;

public class NewsDetailViewModel extends AndroidViewModel{
    private NewsRepository newsRepository;
    private LiveData<NewsItem> newsItemLiveData;

    public NewsDetailViewModel(@NonNull Application application, String newsItemId) {
        super(application);
        newsRepository = new NewsRepository(application);
        newsItemLiveData = newsRepository.getNewsItemById(newsItemId);
    }

    public LiveData<NewsItem> getNewsItem() {
        return newsItemLiveData;
    }
}
