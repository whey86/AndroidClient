package com.erikle2.time;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TimePicker;

import com.erikle2.main.R;

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
        for(int i = 0; i < widget.getChildCount(); i++) {
            View v = widget.getChildAt(i);

            // Look for the title view to ensure this is an indicator and not a divider.
            TextView tv = (TextView)v.findViewById(android.R.id.title);
            if(tv == null) {
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
        if(v.getId() == R.id.bSelectTimeRangeFragment){
            int starthour = startTimePicker.getCurrentHour();
            int startmin = startTimePicker.getCurrentMinute();
            int endHour = endTimePicker.getCurrentHour();
            int endMin = endTimePicker.getCurrentMinute();
            onTimeRangeSelectedListener.onTimeRangeSelected(starthour,startmin,endHour,endMin);
        }
    }

    public static TimepickerDialog newInstance(OnTimeRangeSelectedListener callback, boolean is24HourMode) {
        TimepickerDialog ret = new TimepickerDialog();
        ret.initialize(callback, is24HourMode);
        return ret;
    }


}