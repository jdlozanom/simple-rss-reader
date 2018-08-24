package com.jdlozanom.simplerssreader.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.jdlozanom.simplerssreader.R;
import com.jdlozanom.simplerssreader.data.NewsItem;
import com.jdlozanom.simplerssreader.viewmodel.NewsListViewModel;

import java.util.List;

public class NewsListActivity extends AppCompatActivity implements NewsListAdapter.ListItemClickListener {
    private NewsListViewModel newsListViewModel;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        RecyclerView recyclerView = findViewById(R.id.news_list_recyclerview);
        adapter = new NewsListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        configureViewModel();
    }

    //Setup ViewModel and observe changes on provided data to notify to the list adapter
    private void configureViewModel() {
        newsListViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        newsListViewModel.getNewsList().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItemList) {
                adapter.setNewsList(newsItemList);
            }
        });
    }

    @Override
    public void onListItemClick(String newsItemId) {
        Intent intent = new Intent(NewsListActivity.this, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, newsItemId);
        startActivity(intent);
    }

    private void search(String query) {
        newsListViewModel.getFilteredNewsList(query).observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItemList) {
                adapter.setNewsList(newsItemList);
            }
        });
    }

    //Inflate menu and defines search function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_list, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
