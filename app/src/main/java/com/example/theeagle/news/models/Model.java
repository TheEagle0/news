package com.example.theeagle.news.models;

public class Model {
    private String title, url, section;
    private long time;

    public Model(String title, String url, String section , long time) {
        this.title = title;
        this.url = url;
        this.section = section;
        this.time=time;
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
}
