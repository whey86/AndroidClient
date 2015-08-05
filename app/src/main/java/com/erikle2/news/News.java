package com.erikle2.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Erik on 2015-07-31.
 */
public class News {
    private String type, location, time, title, text;
    private Date date;
    private ArrayList<String> indicator;

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

    public ArrayList<String> getIndicator() {
        return indicator;
    }

    public void setIndicator(ArrayList<String> indicator) {
        this.indicator = indicator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
