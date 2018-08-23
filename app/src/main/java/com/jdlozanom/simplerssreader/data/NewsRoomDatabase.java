package com.jdlozanom.simplerssreader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {
    private static NewsRoomDatabase INSTANCE;

    static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, "news")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract NewsDao newsDao();
}
