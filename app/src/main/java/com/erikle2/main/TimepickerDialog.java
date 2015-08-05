package com.erikle2.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimepickerDialog extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog.Builder tpd = new TimePickerDialog.Builder(getActivity());
        tpd.setPositiveButton("Bekrflameäfta", null);
        tpd.setNegativeButton("Avbryt", null);
        tpd.setMessage("HAHHAHAHAHA");
//                (getActivity(), this, hour, minute,
//                DateFormat.is24HourFormat(getActivity()));
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Create a new instance of TimePickerDialog and return it
        return tpd.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b = this.getArguments();
        String title = (String) b.get("title");
        getDialog().setTitle(title);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }

    public static TimepickerDialog newInstance(Bundle b) {
        TimepickerDialog f = new TimepickerDialog();
        f.setArguments(b);
        return f;
    }
}