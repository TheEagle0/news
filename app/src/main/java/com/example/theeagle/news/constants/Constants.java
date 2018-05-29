package com.example.theeagle.news.constants;

import com.example.theeagle.news.BuildConfig;

public final class Constants {

    private Constants() {
    }

    public static final String URL = "http://content.guardianapis.com/search?api-key="+ BuildConfig.API_KEY+"" +
            "&show-tags=contributor";
    public static final int READ_TIME_OUT = 10000;
    public static final int CONNECT_TIME_OUT = 15000;
    public static final int LOADER_ID = 0;
}
