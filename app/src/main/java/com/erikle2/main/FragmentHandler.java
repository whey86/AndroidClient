package com.erikle2.main;

import android.support.v4.app.Fragment;

import com.erikle2.news.NewsFragment;

/**
 * Class holding initialization av all the fragments picked
 * in the navigationdrawer
 *
 */
public class FragmentHandler {

    public static Fragment getFragment(int posistion){

        if(posistion == 0){
            return NewsFragment.newInstance("","");
        }
        else if(posistion ==1){
            return new TimesFragment();
        }
        else if(posistion ==2){
            return new TimesFragment();
        }
        else return null;
    }
}
