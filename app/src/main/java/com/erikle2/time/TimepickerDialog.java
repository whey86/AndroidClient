package com.erikle2.time;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TimePicker;

import com.erikle2.main.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimepickerDialog extends TimeRangePickerDialog {

    TabHost tabs;
    Button setTimeRange;
    TimePicker startTimePicker, endTimePicker;
    OnTimeRangeSelectedListener onTimeRangeSelectedListener;
    boolean is24HourMode;
    public static final String TIMERANGEPICKER_TAG = "timerangepicker";

    private final int[] WEEKDAYS = new int[]{Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY};
    private int position;
    private TimeListAdapter mAdapter;
    onViewUpdate viewUpdate;

    public  TimepickerDialog(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(me.tittojose.www.timerangepicker_library.R.layout.timerange_picker_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tabs = (TabHost) root.findViewById(me.tittojose.www.timerangepicker_library.R.id.tabHost);

        setTimeRange = (Button) root.findViewById(me.tittojose.www.timerangepicker_library.R.id.bSetTimeRange);

        setTimeRange.setText(getActivity().getResources().getString(R.string.time_confirm));

        setTimeRange.setBackgroundColor(getActivity().getResources().getColor(R.color.primary));
        setTimeRange.setTextColor(getActivity().getResources().getColor(R.color.white));
        startTimePicker = (TimePicker) root.findViewById(me.tittojose.www.timerangepicker_library.R.id.startTimePicker);
        endTimePicker = (TimePicker) root.findViewById(me.tittojose.www.timerangepicker_library.R.id.endTimePicker);
        setTimeRange.setOnClickListener(this);


//        tabs.findViewById(me.tittojose.www.timerangepicker_library.R.id.tabHost);


        tabs.setup();
        TabHost.TabSpec tabpage1 = tabs.newTabSpec("one");
        tabpage1.setContent(me.tittojose.www.timerangepicker_library.R.id.startTimeGroup);
        tabpage1.setIndicator(getActivity().getResources().getString(R.string.get));

        TabHost.TabSpec tabpage2 = tabs.newTabSpec("two");
        tabpage2.setContent(me.tittojose.www.timerangepicker_library.R.id.endTimeGroup);
        tabpage2.setIndicator(getActivity().getResources().getString(R.string.leave));

        tabs.addTab(tabpage1);
        tabs.addTab(tabpage2);

//        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                for (int i = 0; i < tabs.getTabWidget().getChildCount(); i++) {
//                    tabs.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.divider));   //inactive tabs
//                }
////                setTabColor(tabs);
//
//                tabs.getCurrentTabView().setBackgroundColor(getResources().getColor(R.color.primary)); //active ta
//            }
//        });
//        setTabColor(tabs);

        TabWidget widget = tabs.getTabWidget();
        for (int i = 0; i < widget.getChildCount(); i++) {
            View v = widget.getChildAt(i);

            // Look for the title view to ensure this is an indicator and not a divider.
            TextView tv = (TextView) v.findViewById(android.R.id.title);
            if (tv == null) {
                continue;
            }
            v.setBackgroundResource(R.drawable.tab_selector);
        }

        return root;
    }
    //Change The Backgournd Color of Tabs
//    public void setTabColor(TabHost tabhost) {
//
//        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
//            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.GRAY); //unselected
//
//        if(tabhost.getCurrentTab()==0)
//            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.RED); //1st tab selected
//        else
//            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.BLUE); //2nd tab selected
//    }


    @Override
    public void onClick(View v) {
//        super.onClick(v);
        dismiss();
        if (v.getId() == R.id.bSetTimeRange) {
            int starthour = startTimePicker.getCurrentHour();
            int startmin = startTimePicker.getCurrentMinute();
            int endHour = endTimePicker.getCurrentHour();
            int endMin = endTimePicker.getCurrentMinute();

            viewUpdate.updateView(position,starthour, startmin, endHour, endMin);

            Time t = new Time();
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, WEEKDAYS[1]);
            t.setDate(c.getTime());
            t.setStartTime("" + ":ANUS" + "");
            t.setEndTime("" + ":" + "");
            t.timeConfirmed();
            mAdapter.add(t);
//            onTimeRangeSelectedListener.onTimeRangeSelected(starthour, startmin, endHour, endMin);

//            ParseObject gameScore = new ParseObject("time");
//            gameScore.put("startTime", starthour + ":" + startmin);
//            gameScore.put("endTime", endHour + ":" + endMin);
//            gameScore.put("user", ParseUser.getCurrentUser());
//            gameScore.saveInBackground();


        }
    }

    public void setAdapter(TimeListAdapter list) {
        this.mAdapter = list;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    @Override
    public void initialize(OnTimeRangeSelectedListener callback, boolean is24HourMode) {
        super.initialize(callback, is24HourMode);
        this.onTimeRangeSelectedListener = callback;
    }
    public interface onViewUpdate{
         void updateView(int position, int i, int i1, int i2, int i3);
    }

    public void setOnViewUpdate(onViewUpdate callback2){
        this.viewUpdate = callback2;
    }

    public static TimepickerDialog newInstance(OnTimeRangeSelectedListener callback, onViewUpdate callback2, boolean is24HourMode) {
        TimepickerDialog ret = new TimepickerDialog();
        ret.initialize(callback, is24HourMode);
//        ret.setAdapter(adapter);
//        ret.setPosition(pos);
        ret.setOnViewUpdate(callback2);
        return ret;
    }


}