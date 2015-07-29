package com.erikle2.main;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimeListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private final FragmentManager fm;
    private TextView text1, text2;
    private final JSONObject times;

    public TimeListAdapter(Context context, FragmentManager fm, JSONObject times) {
        super(context, R.layout.list_layout_times, context.getResources().getStringArray(R.array.Days));
        this.context = context;
        this.fm = fm;
        this.values = context.getResources().getStringArray(R.array.Days);
        this.times = times;

    }


    @Override
    public View getView(final int position, View convertView,  final ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_layout_times, parent, false);

            TextView day = (TextView) rowView.findViewById(R.id.tv_day);

            final TextView leave = (TextView) rowView.findViewById(R.id.tv_time);
            final TextView get = (TextView) rowView.findViewById(R.id.tv_time2);
            String name = "Error parsing";
            String time1 = "";
            String time2 = "";

            try {
                JSONArray ja = times.getJSONArray("week");
                JSONObject jo = (JSONObject) ja.get(position);
                name = (String) jo.get("name");
                time1 = (String) jo.get("leave");
                time2 = (String) jo.get("get");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("TimeListAdapter", "JSON PARSING FAIL");
            }

            day.setText(name);
            leave.setText(time1);
            get.setText(time2);



           final CheckBox cb = (CheckBox) rowView.findViewById(R.id.cb_confirm);
            Button getIncrease = (Button) rowView.findViewById(R.id.button6);

            getIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setChecked(false);
                    String time = leave.getText().toString();
                    String newTime = increaseTime(time);
                    Log.e("click", newTime);
                    leave.setText(newTime + " - ");
                }
            });
            Button getIncrease2 = (Button) rowView.findViewById(R.id.button8);

            getIncrease2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setChecked(false);
                    String time = get.getText().toString();
                    String newTime = increaseTime(time);
                    Log.e("click", newTime);
                    get.setText(newTime );
                }
            });

            Button getDecrease = (Button) rowView.findViewById(R.id.button5);

            getDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setChecked(false);
                    String time = leave.getText().toString();
                    String newTime = decreaseTime(time);
                    Log.e("click", newTime);
                    leave.setText(newTime+ " - ");
                }
            });
            Button getDecrease2 = (Button) rowView.findViewById(R.id.button7);

            getDecrease2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cb.setChecked(false);
                    String time = get.getText().toString();
                    String newTime = decreaseTime(time);
                    Log.e("click", newTime);
                    get.setText(newTime);
                }
            });
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        leave.setTextColor( parent.getResources().getColor(R.color.check));
                        get.setTextColor( parent.getResources().getColor(R.color.check));
                    }else{
                        leave.setTextColor( parent.getResources().getColor(R.color.unchecked));
                        get.setTextColor( parent.getResources().getColor(R.color.unchecked));
                    }
                }
            });

            return rowView;
        }else{
            return convertView;
        }


    }

    public  String increaseTime(String time) {

        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int min = Integer.parseInt("" + time.charAt(3)+ time.charAt(4));
        String divider = ":";
        min = min + 15;
        if(min >= 60){
            min = 0;
            hour ++;
            if(hour>=24){
                hour = 0;
            }
        }
        if(min<10){
            divider += "0";
        }
        String ret;
        if(hour<10)
        {
            ret = "0" + hour + divider +  min;
        }else{
            ret = hour + divider +  min;
        }

        return ret;
    }

    public  String decreaseTime(String time) {

        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int min = Integer.parseInt("" + time.charAt(3)+ time.charAt(4));
        String divider = ":";
        min = min - 15;
        if(min < 0){
            min = 45;
            hour --;
            if(hour<0){
                hour = 23;
            }
        }
        if(min<10){
            divider += "0";
        }
        String ret;
        if(hour<10)
        {
            ret = "0" + hour + divider +  min;
        }else{
            ret = hour + divider +  min;
        }

        return ret;
    }



}
