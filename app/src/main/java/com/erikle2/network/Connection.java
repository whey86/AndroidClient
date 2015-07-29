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
            JSONObject jo = new JSONObject();
            jo.put("type","event");
            jo.put("title","Utflykt");
            jo.put("text","Imorgon ska vi gå ut i skogen");

            JSONObject jo2 = new JSONObject();
            jo2.put("type","event");
            jo2.put("title","Utflykt");
            jo2.put("text","Imorgon ska vi gå ut i skogen");

            feed.put(0,jo);
            feed.put(1,jo2);
            mainObj.put("newsfeed", feed);
        }catch (JSONException e){
            e.printStackTrace();
        }



        return mainObj;

    };
}
