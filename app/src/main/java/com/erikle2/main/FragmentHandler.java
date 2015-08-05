package com.erikle2.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.erikle2.news.NewsFragment;
import com.erikle2.time.TimesFragment;

/**
 * Class holding initialization av all the fragments picked
 * in the navigationdrawer
 *
 */
public class FragmentHandler {

    public final static String FRAGMENT_TIME = "time";
    public final static String FRAGMENT_NEWS = "news";
    public final static String FRAGMENT_SICK = "sick";
    public static void getFragment(FragmentManager fm, int position){

        //
        Fragment f = null;
        FragmentTransaction ft = null;
        String tag= "";
        if(position == 0){
            f = NewsFragment.newInstance();
            ft = fm.beginTransaction()
                    .replace(R.id.container, f);
            tag = FRAGMENT_NEWS;
        }
        else if(position ==1){

            if(fm.findFragmentByTag(FRAGMENT_TIME) == null){
                Log.e("fragment time","NULLt ");
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
                ft.commit();

    };
}
