package com.example.theeagle.news.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.theeagle.news.R;
import com.example.theeagle.news.adapters.Adapter;
import com.example.theeagle.news.constants.Constants;
import com.example.theeagle.news.models.Model;
import com.example.theeagle.news.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> newsFeed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(newsFeed, this);
        recyclerView.setAdapter(adapter);
    }
    private static class Loader extends AsyncTaskLoader {
        public Loader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public ArrayList<Model> loadInBackground() {

            return null;
        }
    }
}
