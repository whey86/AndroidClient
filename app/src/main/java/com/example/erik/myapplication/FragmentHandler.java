package com.example.erik.myapplication;

import android.support.v4.app.Fragment;

/**
 * Created by Erik on 2015-07-21.
 */
public class FragmentHandler {

    public static Fragment getFragment(int posistion){

        if(posistion == 0){
            return new TimesFragment();
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
