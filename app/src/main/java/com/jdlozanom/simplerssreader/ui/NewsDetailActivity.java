package com.jdlozanom.simplerssreader.ui;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdlozanom.simplerssreader.R;
import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.utils.HtmlUtils;
import com.jdlozanom.simplerssreader.viewmodel.NewsDetailViewModel;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    public final static String EXTRA_NEWS_ID = "EXTRA_NEWS_ID";
    private NewsDetailViewModel newsDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_detail);

        newsDetailViewModel = ViewModelProviders.of(this, new NewsDetailViewModelFactory(this.getApplication(), getIntent().getStringExtra(EXTRA_NEWS_ID))).get(NewsDetailViewModel.class);
        newsDetailViewModel.getNewsItem().observe(this, new Observer<NewsItem>() {
            @Override
            public void onChanged(@Nullable NewsItem newsItem) {
                if (newsItem != null) loadNewsItemViews(newsItem);
            }
        });
    }

    public void loadNewsItemViews(final NewsItem newsItem) {
        ((TextView) findViewById(R.id.titleTextView)).setText(newsItem.getTitle());
        ((TextView) findViewById(R.id.descriptionTextView)).setText(Html.fromHtml(HtmlUtils.removeImageTags(newsItem.getDescription())));
        Picasso.with(this).load(HtmlUtils.getFirstImage(newsItem.getDescription())).into((ImageView) findViewById(R.id.detailImageView));

        findViewById(R.id.linkButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse(newsItem.getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    //Custom ViewModelFactory to pass news item id parameter on ViewModel construction
    private class NewsDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private Application application;
        private String newsItemId;

        NewsDetailViewModelFactory(Application application, String newsItemId) {
            this.application = application;
            this.newsItemId = newsItemId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new NewsDetailViewModel(application, newsItemId);
        }
    }
}
