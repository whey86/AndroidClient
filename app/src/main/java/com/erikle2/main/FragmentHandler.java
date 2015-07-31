package com.erikle2.main;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.app.FragmentManager;
import android.app.Fragment;
import android.util.Log;

import com.erikle2.news.NewsFragment;

/**
 * Class holding initialization av all the fragments picked
 * in the navigationdrawer
 *
 */
public class FragmentHandler {

    public static void getFragment(FragmentManager fm, int position){

        //
        Fragment f = null;
        FragmentTransaction ft = null;
        String tag= "";
        if(position == 0){
            f = NewsFragment.newInstance("","");
            ft = fm.beginTransaction()
                    .replace(R.id.container, f);
            tag = "news";
        }
        else if(position ==1){

            if(fm.findFragmentByTag("time") == null){
                Log.e("fragment time","NULLt ");
                tag="time";
                f = new TimesFragment();
                ft = fm.beginTransaction()
                        .replace(R.id.container,f,tag ).addToBackStack(tag);
            }else{
                Log.e("fragment time","EXIST");
                f=fm.findFragmentByTag("time");
                ft = fm.beginTransaction()
                        .replace(R.id.container, f);
            }

        }
        else if(position ==2){
            f = new Fragment();
        }
        Log.e("fragment TAG",tag);
                ft.commit();

    };
}
