package com.erikle2.news;

/**
 * Created by Erik on 2015-07-31.
 */
public class News {
    private String type, location, time, title, text;
    private int [] indicator;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getIndicator() {
        return indicator;
    }

    public void setIndicator(int[] indicator) {
        this.indicator = indicator;
    }
}
