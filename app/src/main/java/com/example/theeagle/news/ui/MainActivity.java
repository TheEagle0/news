package com.example.theeagle.news.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.theeagle.news.R;
import com.example.theeagle.news.adapters.NewsFeedAdapter;
import com.example.theeagle.news.constants.Constants;
import com.example.theeagle.news.models.NewsFeed;
import com.example.theeagle.news.utils.CheckConnectivity;
import com.example.theeagle.news.utils.NetworkingHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<ArrayList<NewsFeed>> {

    private static final String TAG = "MainActivity";
    private TextView emptyState;
    private final ArrayList<NewsFeed> newsFeed = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_item:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillUi();

    }

    private void fillUi() {
        if (CheckConnectivity.checkNetwork(getApplication())) {
            getNews();
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }


    private void getNews() {
        getSupportLoaderManager().initLoader(Constants.LOADER_ID, null, this).forceLoad();
    }

    private void initView() {
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(newsFeed, this);
        recyclerView.setAdapter(newsFeedAdapter);
        emptyState = findViewById(R.id.empty_state_text);
    }

    @NonNull
    @Override
    public Loader<ArrayList<NewsFeed>> onCreateLoader(int id, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new GetNewsTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<NewsFeed>> loader,
                               ArrayList<NewsFeed> data) {
        progressBar.setVisibility(View.INVISIBLE);
        if (data != null)
            ((NewsFeedAdapter) recyclerView.getAdapter()).updateAdapter(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<NewsFeed>> loader) {
    }


    private static class GetNewsTask extends AsyncTaskLoader<ArrayList<NewsFeed>> {

        @Nullable
        private ArrayList<NewsFeed> data;

        @Override
        public void deliverResult(@Nullable ArrayList<NewsFeed> data) {
            this.data = data;
            super.deliverResult(data);
        }

        GetNewsTask(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onForceLoad() {
            if (data == null)
                super.onForceLoad();
            else
                deliverResult(data);
        }

        @Nullable
        @Override
        public ArrayList<NewsFeed> loadInBackground() {
            Log.d(TAG, "loadInBackground");
            return NetworkingHandler.fetchData(Constants.url);
        }
    }
}
