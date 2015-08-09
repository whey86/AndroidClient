package com.erikle2.time;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikle2.main.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * ArrayAdapter för time listview
 */
public class TimeListAdapter extends ArrayAdapter<Time> {
    /**
     * Reference to context
     */
    private final Context context;

//    private final String[] values;
    /**
     * Refernce to fragment maneger
     */
    private final FragmentManager fm;

    //    private TextView text1, text2;
    private final ArrayList times;

    /**
     * Constructor
     *
     * @param context
     * @param fm
     * @param times
     */
    public TimeListAdapter(Context context, FragmentManager fm, ArrayList<Time> times) {
        super(context, R.layout.list_layout_times, times);
        this.context = context;
        this.fm = fm;
//        this.values = context.getResources().getStringArray(R.array.Days);
        this.times = times;

    }

    /**
     * Returns the view of the listitem
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_layout_times, parent, false);

            // Ref to the views
//            ImageView img = (ImageView) rowView.findViewById(R.id.list_icon);
//            img.setImageResource(R.drawable.expand_more);
            TextView day = (TextView) rowView.findViewById(R.id.tv_day);
            TextView leave = (TextView) rowView.findViewById(R.id.tv_time);
            TextView get = (TextView) rowView.findViewById(R.id.tv_time2);

            //
            String name;
            String time1;
            String time2;

            //Data item for this
            Time time = getItem(position);

            //Get day of the week from date
            Calendar cal = Calendar.getInstance();
            cal.setTime(time.getDate());
            int dayoftheweek = cal.get(Calendar.DAY_OF_WEEK);

            // Set title
            name = "" + context.getResources().getStringArray(R.array.Days)[dayoftheweek - 2] + "  " + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH);

            //Set times
            time1 = time.getStartTime();
            time2 = time.getEndTime();

            //Init timeItem GUI
            day.setText(name);
            leave.setText(time1);
            get.setText(time2);

            //Set background
            LinearLayout ll = (LinearLayout) rowView.findViewById(R.id.item_container);
            ll.setBackgroundResource(time.getBackground());


            return rowView;
        } else {
            View rowView = convertView;

            // Ref to the views
//            ImageView img = (ImageView) rowView.findViewById(R.id.list_icon);
//            img.setImageResource(R.drawable.expand_more);
            TextView day = (TextView) rowView.findViewById(R.id.tv_day);
            TextView leave = (TextView) rowView.findViewById(R.id.tv_time);
            TextView get = (TextView) rowView.findViewById(R.id.tv_time2);

            //
            String name;
            String time1;
            String time2;

            //Data item for this
            Time time = getItem(position);

            //Get day of the week from date
            Calendar cal = Calendar.getInstance();
            cal.setTime(time.getDate());
            int dayoftheweek = cal.get(Calendar.DAY_OF_WEEK);

            // Set title
            name = "" + context.getResources().getStringArray(R.array.Days)[dayoftheweek - 2] + "  " + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH);

            //Set times
            time1 = time.getStartTime();
            time2 = time.getEndTime();

            //Init timeItem GUI
            day.setText(name);
            leave.setText(time1);
            get.setText(time2);

            //Set background
            LinearLayout ll = (LinearLayout) rowView.findViewById(R.id.item_container);
            ll.setBackgroundResource(time.getBackground());

            return rowView;
        }


    }


    /**
     * Transfrom time to a new string with 15+ min
     *
     * @param time
     * @return
     */
    public String increaseTime(String time) {

        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int min = Integer.parseInt("" + time.charAt(3) + time.charAt(4));
        String divider = ":";
        min = min + 15;
        if (min >= 60) {
            min = 0;
            hour++;
            if (hour >= 24) {
                hour = 0;
            }
        }
        if (min < 10) {
            divider += "0";
        }
        String ret;
        if (hour < 10) {
            ret = "0" + hour + divider + min;
        } else {
            ret = hour + divider + min;
        }

        return ret;
    }

    /**
     * Transforms a string to a new string -15 min
     *
     * @param time
     * @return
     */
    public String decreaseTime(String time) {

        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int min = Integer.parseInt("" + time.charAt(3) + time.charAt(4));
        String divider = ":";
        min = min - 15;
        if (min < 0) {
            min = 45;
            hour--;
            if (hour < 0) {
                hour = 23;
            }
        }
        if (min < 10) {
            divider += "0";
        }
        String ret;
        if (hour < 10) {
            ret = "0" + hour + divider + min;
        } else {
            ret = hour + divider + min;
        }

        return ret;
    }
}

//           final CheckBox cb = (CheckBox) rowView.findViewById(R.id.cb_confirm);
//            Button getIncrease = (Button) rowView.findViewById(R.id.button6);
//TODO: Decide to use this expand view or dialog to select time
//            getIncrease.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cb.setChecked(false);
//                    String time = leave.getText().toString();
//                    String newTime = increaseTime(time);
//                    Log.e("click", newTime);
//                    leave.setText(newTime + " - ");
//                }
//            });
//            Button getIncrease2 = (Button) rowView.findViewById(R.id.button8);
//
//            getIncrease2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cb.setChecked(false);
//                    String time = get.getText().toString();
//                    String newTime = increaseTime(time);
//                    Log.e("click", newTime);
//                    get.setText(newTime );
//                }
//            });
//
//            Button getDecrease = (Button) rowView.findViewById(R.id.button5);
//
//            getDecrease.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cb.setChecked(false);
//                    String time = leave.getText().toString();
//                    String newTime = decreaseTime(time);
//                    Log.e("click", newTime);
//                    leave.setText(newTime+ " - ");
//                }
//            });
//            Button getDecrease2 = (Button) rowView.findViewById(R.id.button7);
//
//            getDecrease2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cb.setChecked(false);
//                    String time = get.getText().toString();
//                    String newTime = decreaseTime(time);
//                    Log.e("click", newTime);
//                    get.setText(newTime);
//                }
//            });
//            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//                        leave.setTextColor( parent.getResources().getColor(R.color.check));
//                        get.setTextColor( parent.getResources().getColor(R.color.check));
//                    }else{
//                        leave.setTextColor( parent.getResources().getColor(R.color.unchecked));
//                        get.setTextColor( parent.getResources().getColor(R.color.unchecked));
//                    }
//                }
//            });