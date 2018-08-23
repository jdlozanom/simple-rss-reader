package com.jdlozanom.simplerssreader.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class NewsItem {
    @NonNull
    @PrimaryKey
    private String id;
    private String title;
    private String imageUrl;
    private String description;
    private String link;

    public NewsItem(@NonNull String id, String title, String imageUrl, String description, String link) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.link = link;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

}
