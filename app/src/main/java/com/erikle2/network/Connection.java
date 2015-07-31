package com.erikle2.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erik on 2015-07-28.
 */
public class Connection {

    public JSONObject getNews(){

        JSONArray feed = new JSONArray();
        JSONObject mainObj = new JSONObject();

        try{
            JSONArray icons = new JSONArray();
            icons.put(0);
            icons.put(1);

            JSONObject jo = new JSONObject();
            jo.put("type","event");
            jo.put("title","Utflykt");
            jo.put("text","Imorgon ska vi g� ut i skogen och plocka bl�b�r");
            jo.put("location","Älvsjö");
            jo.put("time","11-17");
            jo.put("icons",icons);

            JSONObject jo2 = new JSONObject();
            jo2.put("type","event");
            jo2.put("title","Möte");
            jo2.put("text","Imorgon ska vi g� ut i skogen");
            jo2.put("icons",icons);
            jo2.put("location","Hökis");
            jo2.put("time","11-17");

            feed.put(0,jo);
            feed.put(1,jo2);
            mainObj.put("newsfeed", feed);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mainObj;

    };
    public JSONObject getTimes(){
        JSONArray week = new JSONArray();

        String [] days = new String[]{"M�ndag","Tisdag","Onsdag","Torsdag","Fredag"};

        for(int i = 0; i<5;i++){
            JSONObject day = new JSONObject();
            String s =days[i];
            try{
                day.put("name",s);
                day.put("leave","08:00 - ");
                day.put("get","16:00");
            }catch(JSONException e){
                e.printStackTrace();
            }
            week.put(day);

        }
        JSONObject mainObj = new JSONObject();
        try{
            mainObj.put("weeknr",31);
            mainObj.put("week", week);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return mainObj;

    }
}
