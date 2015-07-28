package com.example.erik.myapplication;

/**
 * Created by Erik on 2015-07-27.
 */
public class StringParser {



    public  String increaseTime(String time) {

        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int min = Integer.parseInt("" + time.charAt(3)+ time.charAt(4));
        String divider = ":";
        min = min + 15;
        if(min >= 60){
            hour ++;
            if(hour>24){
                hour++;
            }
        }
        if(min<10){
            divider += "0";
        }
        String ret;
        if(hour<10)
        {
            ret = "0" + hour + divider +  min + " - ";
        }else{
            ret = hour + divider +  min + " - ";
        }

        return ret;
    }
}
