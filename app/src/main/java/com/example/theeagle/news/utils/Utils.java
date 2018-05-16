package com.example.theeagle.news.utils;

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

public final class Utils {

    public static ArrayList<NewsFeed> fetchData(String requestUrl) {
        Log.d("Utils", "fetchData");
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
            urlConnection.setReadTimeout(Constants.readTimeOut);
            urlConnection.setConnectTimeout(Constants.connectTimeOut);
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

    private static ArrayList<NewsFeed> getDataFromJson(String json) {
        ArrayList<NewsFeed> news = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            JSONObject baseObject = new JSONObject(json);
            JSONObject response = baseObject.getJSONObject("response");
            JSONArray newsObjects = response.getJSONArray("results");
            for (int i = 0; i < newsObjects.length(); i++) {
                JSONObject getArticle = newsObjects.getJSONObject(i);
                String title = getArticle.getString("webTitle");
                String sectionName = getArticle.getString("sectionName");
                String time = getArticle.getString("webPublicationDate");
                String url = getArticle.getString("webUrl");

                news.add(new NewsFeed(title, url, sectionName, time));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }
}
