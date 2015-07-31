package com.erikle2.main;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.erikle2.network.Connection;
import com.parse.Parse;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //parse setup
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "YHnk0FZY29TDdjPbCpLowiwiQ7fi5AIGFg0C5TWO", "CFP96ldVeaYpMCumYVCYih587AVSJvJHoRyBAqVz");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        setContentView(R.layout.activity_main);

        //Get navigation drawer fragment
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        //Actionbar title
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                //Navigation drawer reference XML
                R.id.navigation_drawer,
                //Navigation drawer layout
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //Set color of actionbar
        Resources res = getResources();
        ColorDrawable cd = new ColorDrawable(res.getColor(R.color.primary));
        getSupportActionBar().setBackgroundDrawable(cd);


    }

    /**
     * Decides with fragment to view on selection
     * @param position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentHandler.getFragment( getFragmentManager(),position);
        onSectionAttached(position);
    }

    /**
     * Sets the title of the actionbar
     * @param number
     */
    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getResources().getString(R.string.title_section1);
                break;
            case 1:
                Connection c = new Connection();
               JSONObject j=  c.getTimes();
                try{
                    mTitle = "Vecka " + j.get("weeknr");
                }catch (JSONException e){
                    e.printStackTrace();
                }

                break;
            case 2:
                mTitle = getResources().getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
