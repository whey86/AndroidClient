package com.example.erik.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimeListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public TimeListAdapter(Context context, String[] values){
        super(context,R.layout.list_layout_times,values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_layout_times,parent, false);

        return rowView;

    }



}
