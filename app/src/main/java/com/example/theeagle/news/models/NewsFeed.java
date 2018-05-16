package com.example.theeagle.news.models;

public class NewsFeed {

    private String title, url, section, time;

    public NewsFeed(String title, String url, String section, String time) {
        this.title = title;
        this.url = url;
        this.section = section;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String authorName) {
        this.url = authorName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
