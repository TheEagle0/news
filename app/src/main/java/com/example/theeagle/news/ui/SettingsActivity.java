package com.example.theeagle.news.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.theeagle.news.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView categoriesTextView;
    private PreferenceScreen preferenceScreen=new PreferenceScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        listeners();

    }

    private void listeners() {
        categoriesTextView.setOnClickListener(this);
    }

    private void initViews() {
        categoriesTextView = findViewById(R.id.categories);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.categories:
                addFragment();
                break;
        }
    }

    private void addFragment() {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, preferenceScreen);
        fragmentTransaction.commit();    }
}
