package com.jdlozanom.simplerssreader.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsitem")
    LiveData<List<NewsItem>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsItem(NewsItem newsItem);
}
