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
import com.parse.GetCallback;
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
    TimeListAdapter adapter;
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

            adapter = new TimeListAdapter(getActivity(), getActivity().getFragmentManager(), new ArrayList<Time>());
            listViewTimes.setAdapter(adapter);
            initTimes();


//            //ListItem is clicked
            listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                    TimepickerDialog timePickerDialog = TimepickerDialog.newInstance(TimesFragment.this, TimesFragment.this, true, position);

                    timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);
//

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

    private void initTimes() {
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
        final int currentWeek = now.get(Calendar.WEEK_OF_YEAR);
        Log.d("Currentweek", "" + currentWeek);
        //init arraylist with default data
        for (int j = 0; j < 5; j++) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, WEEKDAYS[j]);
            Time t = new Time();
            t.setStartTime("START");
            t.setEndTime("END");
            t.setDate(c.getTime());
            t.setWeek(currentWeek);
            adapter.add(t);
        }

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DAY_OF_WEEK, WEEKDAYS[0]);
        Date start = c1.getTime();
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.DAY_OF_WEEK, WEEKDAYS[0]);
        Date end = c2.getTime();
//        Fetch times from backend to make sure user see the latest sent times
        ParseQuery<ParseObject> query = ParseQuery.getQuery("time");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("week", currentWeek);
//        query.whereLessThanOrEqualTo("date", end);


        Log.d("Parse", "Fetching time data" + "");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> timeList, ParseException e) {


                for (ParseObject item : timeList) {
                    Date d = item.getDate("date");
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(d);
                    int weekday = c1.get(Calendar.DAY_OF_WEEK);


                    Log.d("Parse", "Crruent week " + weekday);

                    Time time = adapter.getItem(weekday-2);
                    time.setStartTime(item.getString("startTime"));
                    time.setEndTime(item.getString("endTime"));
                    time.setDate(d);
                    time.setWeek(currentWeek);
                    time.timeConfirmed();

                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTimeRangeSelected(int i, int i1, int i2, int i3) {


    }

    @Override
    public void updateView(int position, int startHour, int startMin, int endHour, int endMin) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, WEEKDAYS[position]);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);




        Time t = adapter.getItem(position);

        t.setDate(c.getTime());
        t.setStartTime(addZero(startHour) + ":" + addZero(startMin));
        t.setEndTime(addZero(endHour) + ":" + addZero(endMin));
        t.timeConfirmed();
        t.setWeek(c.get(Calendar.WEEK_OF_YEAR));
        t.setDay(position);

        adapter.notifyDataSetChanged();

        addTime(t,startHour,startMin,endHour,endMin,c,position);
    }

    private String addZero(int n) {
        if (n < 10) {
            return "0" + n;
        } else return n + "";
    }

    private void addTime(final Time t,final int startHour,final int startMin,final int endHour,final int endMin,final Calendar c, final int pos){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("time");
        query.whereEqualTo("user", ParseUser.getCurrentUser());

        Calendar cStart = c;
        Calendar cEnd = c;
        query.whereEqualTo("week", t.getWeek());
        query.whereEqualTo("day",pos);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    parseObject.put("startTime", t.getStartTime());
                    parseObject.put("endTime", t.getEndTime());
                    parseObject.saveInBackground();
                } else {

                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                        ParseObject timeStamp = new ParseObject("time");
                        timeStamp.put("startTime", addZero(startHour) + ":" + addZero(startMin));
                        timeStamp.put("endTime", addZero(endHour) + ":" + addZero(endMin));
                        timeStamp.put("user", ParseUser.getCurrentUser());
                        timeStamp.put("date", c.getTime());
                        timeStamp.put("week",c.get(Calendar.WEEK_OF_YEAR));
                        timeStamp.put("day",pos);
                        timeStamp.saveInBackground();
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        });











    }
}
