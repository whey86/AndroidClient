package com.erikle2.login;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Erik on 2015-08-03.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "YHnk0FZY29TDdjPbCpLowiwiQ7fi5AIGFg0C5TWO", "CFP96ldVeaYpMCumYVCYih587AVSJvJHoRyBAqVz");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.

        ParseACL.setDefaultACL(defaultACL, true);
    }

}

