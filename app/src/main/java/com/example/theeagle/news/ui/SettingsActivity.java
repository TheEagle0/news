package com.example.theeagle.news.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.theeagle.news.R;

public class SettingsActivity extends AppCompatActivity {

    private PreferenceScreen preferenceScreen = new PreferenceScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        addFragment();
    }

    private void addFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, preferenceScreen);
        fragmentTransaction.commit();
    }
}