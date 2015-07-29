package com.erikle2.main;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

/**
 * Created by Erik on 2015-07-28.
 */
public class JSONHandler {

    public void saveJson(Context context,JSONObject json){
        String str = json.toString();
        SharedPreferences sharedPref = context.getSharedPreferences("appData", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor prefEditor = context.getSharedPreferences("appData", Context.MODE_WORLD_WRITEABLE).edit();
        prefEditor.putString( "json", str );
        prefEditor.commit();
    }
}
