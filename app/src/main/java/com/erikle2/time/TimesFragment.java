package com.erikle2.time;

import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.erikle2.main.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimesFragment extends Fragment implements TimeRangePickerDialog.OnTimeRangeSelectedListener, TimepickerDialog.onViewUpdate {

    Button btnPrevious;
    Button btnNext;
    ListView listViewTimes;
    private int lastClick = -1;
    private final int[] WEEKDAYS = new int[]{Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY};

    public static final String TIMERANGEPICKER_TAG = "timerangepicker";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //If no saved instance create new view

        if (savedInstanceState == null) {
            LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_times, container, false);

//            btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
//            btnNext = (Button)v.findViewById(R.id.btnNext);


//            try {
//                String week = theWeek.getString("weeknr");
//                weekTitle.setText("Vecka " + week);

//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            listViewTimes = (ListView) v.findViewById(R.id.lv_times);

             final TimeListAdapter adapter = new TimeListAdapter(getActivity(), getActivity().getFragmentManager(), new ArrayList<Time>());
            listViewTimes.setAdapter(adapter);
            initTimes(adapter);


            //ListItem is clicked
            listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TimepickerDialog timePickerDialog = TimepickerDialog.newInstance(TimesFragment.this, TimesFragment.this, true);

                    timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);

                    TimepickerDialog.onViewUpdate()
                }
            });

            return v;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void initTimes(TimeListAdapter adapter) {
//        JSONArray week = new JSONArray();
//
//        for(int i = 0; i<5;i++){
//            JSONObject day = new JSONObject();
//           String s = getResources().getStringArray(R.array.Days)[i];
//            try{
//                day.put("name",s);
//                day.put("leave","08:00 - ");
//                day.put("get","16:00");
//            }catch(JSONException e){
//                e.printStackTrace();
//            }
//            week.put(day);
//
//        }
//        JSONObject mainObj = new JSONObject();
//        try{
//            mainObj.put("weeknr",31);
//            mainObj.put("week", week);
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//            return mainObj;
        // Assume ParseObject myPost was previously created.
        // Assume ParseObject myPost was previously created.

        ArrayList<Time> list = new ArrayList<>();

        //
        Calendar now = Calendar.getInstance();
        int currentWeek = now.get(Calendar.WEEK_OF_YEAR);

        //init arraylist with default data
        for (int j = 0; j < 5; j++) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, WEEKDAYS[j]);
            Time t = new Time();
            t.setStartTime("START");
            t.setEndTime("END");
            t.setDate(c.getTime());
            adapter.add(t);
        }

        //Fetch times from backend to make sure user see the latest sent times
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("time");
//        query.whereEqualTo("user", ParseUser.getCurrentUser());
//        query.whereEqualTo("week", currentWeek);
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> timeList, ParseException e) {
//
//
//                for (ParseObject item : timeList) {
//                    for (int i = 0; i < 5; i++) {
//                        Date d = item.getDate("date");
//                        if (d == mWeek.get(i).getDate()) {
//                            mWeek.get(i).setDate(item.getDate("date"));
//                            mWeek.get(i).setStartTime(item.getString("startTime"));
//                            mWeek.get(i).setEndTime(item.getString("endTime"));
//                        }
//                    }
//
//                }
//            }
//        });
    }

    @Override
    public void onTimeRangeSelected(int i, int i1, int i2, int i3) {


    }

    @Override
    public void updateView(int position, int i, int i1, int i2, int i3) {
        Log.d("UPDATE VIEW", "HAHAHA POSITION " + position);

//        Time t = new Time();
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.DAY_OF_WEEK, WEEKDAYS[1]);
//        t.setDate(c.getTime());
//        t.setStartTime( + ":" + "");
//        t.setEndTime("" + ":" + "");
//        t.timeConfirmed();
//        adapter.add(t);


    }
}
