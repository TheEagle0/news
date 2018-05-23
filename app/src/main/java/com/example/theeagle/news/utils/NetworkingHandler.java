package com.example.theeagle.news.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.theeagle.news.constants.Constants;
import com.example.theeagle.news.models.NewsFeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class NetworkingHandler {

    @Nullable
    public static ArrayList<NewsFeed> fetchData(String requestUrl) {
        Log.d("NetworkingHandler", "fetchData");
        URL url = createUrl(requestUrl);
        String json;
        json = makeHttpRequest(url);
        return getDataFromJson(json);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url == null) {
            return jsonResponse;
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(Constants.READ_TIME_OUT);
            urlConnection.setConnectTimeout(Constants.CONNECT_TIME_OUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder finalOutPut = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            try {
                String fetchedLine = reader.readLine();
                while (fetchedLine != null) {
                    finalOutPut.append(fetchedLine);
                    fetchedLine = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return finalOutPut.toString();
    }

    @Nullable
    private static ArrayList<NewsFeed> getDataFromJson(@Nullable final String json) {
        String title, sectionName, time, author = null;
        final ArrayList<NewsFeed> news = new ArrayList<>();
        if (TextUtils.isEmpty(json)) return null;
        try {
            final JSONObject baseObject = new JSONObject(json);
            final JSONObject response = baseObject.getJSONObject("response");
            final JSONArray newsObjects = response.getJSONArray("results");
            for (int i = 0; i < newsObjects.length(); i++) {
                final JSONObject getArticle = newsObjects.getJSONObject(i);
                title = getArticle.getString("webTitle");
                sectionName = getArticle.getString("sectionName");
                time = getArticle.getString("webPublicationDate");

                final String url = getArticle.getString("webUrl");
                final JSONArray getTags = getArticle.getJSONArray("tags");
                for (int j = 0; j < getTags.length(); j++) {
                    final JSONObject getAuthor = getTags.getJSONObject(j);
                    author = getAuthor.getString("webTitle");
                }
                news.add(new NewsFeed(title, url, sectionName, time, author));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }
}
