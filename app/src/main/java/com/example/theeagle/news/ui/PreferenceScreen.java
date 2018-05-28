package com.example.theeagle.news.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.theeagle.news.R;


public class PreferenceScreen extends android.preference.PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrrence);
    }

}
