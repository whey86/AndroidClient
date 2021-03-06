package com.erikle2.time;

import com.erikle2.main.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Erik on 2015-08-06.
 */
public class Time {
    private String startTime, endTime;
    private int week;
    Date date;
    int background = R.color.unchecked;
    int color1 = R.color.unchecked;
    int color2 = R.color.check;
    int day;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
    public void timeConfirmed (){
        background = color2;
    }
    public void timeCancelled(){background = color1;}

    public int getWeek() {
        return week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
