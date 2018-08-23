package com.jdlozanom.simplerssreader.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jdlozanom.simplerssreader.api.RestClient;
import com.jdlozanom.simplerssreader.api.model.Feed;
import com.jdlozanom.simplerssreader.api.model.ItemFeed;
import com.jdlozanom.simplerssreader.utils.HtmlUtils;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NewsRepository {

    private final String endpointUrl = "https://www.xatakandroid.com/";

    private NewsDao newsDao;
    private LiveData<List<NewsItem>> newsList;

    public NewsRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application);
        newsDao = db.newsDao();
        newsList = newsDao.getAllNews();
    }

    public LiveData<NewsItem> getNewsItemById(String newsItemId){
        return newsDao.getNewsItemById(newsItemId);
    }

    //Access to rrs endpoint to get News and save them in database
    public LiveData<List<NewsItem>> getNewsList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpointUrl)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RestClient restClient = retrofit.create(RestClient.class);
        Call<Feed> call = restClient.getData();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                switch (response.code()) {
                    case 200:
                        Feed feed = response.body();
                        insertFeed(feed);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            }
        });
        return newsList;
    }

    private void insertFeed(Feed feed) {
        List<ItemFeed> itemFeeds = feed.getChannelFeed().getItems();
        for (ItemFeed item : itemFeeds) {
            NewsItem newsItem = new NewsItem(item.getId(), item.getTitle(),  HtmlUtils.getFirstImage(item.getDescription()), item.getDescription(), item.getLink());
            insertNewsItem(newsItem);
        }
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
