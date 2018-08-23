package com.jdlozanom.simplerssreader.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NewsRepository {

    private NewsDao newsDao;
    private LiveData<List<NewsItem>> newsList;

    public NewsRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        newsDao = db.newsDao();
        newsList = newsDao.getAllNews();
    }

    //Access to rrs endpoint to get News and save them in database
    public LiveData<List<NewsItem>> getNewsList() {
        //TODO
        return newsList;
    }

    private void insertNewsItem(NewsItem newsItem) {
        new insertAsyncTask(newsDao).execute(newsItem);
    }

    //AsyncTask to insert News into database
    private static class insertAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            mAsyncTaskDao.insertNewsItem(params[0]);
            return null;
        }
    }
}
