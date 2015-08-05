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

import com.erikle2.main.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimesFragment extends Fragment implements TimeRangePickerDialog.OnTimeRangeSelectedListener {

    Button btnPrevious;
    Button btnNext;
    ListView listViewTimes;
    JSONObject theWeek;
    private int lastClick = -1;

    public static final String TIMERANGEPICKER_TAG = "timerangepicker";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //If no saved instance create new view

        if(savedInstanceState == null){
            LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_times,container,false);

//            btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
//            btnNext = (Button)v.findViewById(R.id.btnNext);

//            TextView weekTitle = (TextView) v.findViewById(R.id.tv_frag_title);
            theWeek = getTimes();
//            try {
//                String week = theWeek.getString("weeknr");
//                weekTitle.setText("Vecka " + week);

//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            listViewTimes = (ListView) v.findViewById(R.id.lv_times);

            TimeListAdapter adapter = new TimeListAdapter(getActivity().getApplicationContext(), getActivity().getFragmentManager(),theWeek);
            listViewTimes.setAdapter(adapter);

            //ListItem is clicked
            listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                    Log.e("Last click", "" + lastClick);
//                    int preClick = lastClick;
//                    lastClick =position;
//
//                    if(preClick != -1){
//                        Log.e("Previous click", "" + preClick);
//
//                        View preView =  parent.getAdapter().getView(preClick,null,parent);
//                        TextView title = (TextView)preView.findViewById(R.id.tv_day);
//                        String sTitle = (String)title.getText();
//                        Log.e("preView title",""+ sTitle);
//                        View preToolbar = preView.findViewById(R.id.toolbar);
//
//                        // Creating the expand animation for the item
//                        ExpandAnimation preExpand = new ExpandAnimation(preToolbar, 500,1);
//                        // Start the animation on the toolbar
//                        preToolbar.startAnimation(preExpand);
//                    }
//
//
//                    View toolbar = view.findViewById(R.id.toolbar);
//
//                        // Creating the expand animation for the item
//                    ExpandAnimation curExpand = new ExpandAnimation(toolbar, 500,0);
//                    if(curExpand.isExpanded()){
//                        ImageView img = (ImageView)view.findViewById(R.id.list_icon);
//                        img.setImageResource(R.drawable.expand_less);
//                    }else{
//                        ImageView img = (ImageView)view.findViewById(R.id.list_icon);
//                        img.setImageResource(R.drawable.expand_more);
//                    }
//                    // Start the animation on the toolbar
//                    toolbar.startAnimation(curExpand);
//    Bundle b = new Bundle();
                    final TimepickerDialog timePickerDialog = TimepickerDialog.newInstance(TimesFragment.this,true);

                    timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMERANGEPICKER_TAG);


                }
        });



            return v;
        }else{
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private JSONObject getTimes(){
        JSONArray week = new JSONArray();

        for(int i = 0; i<5;i++){
            JSONObject day = new JSONObject();
           String s = getResources().getStringArray(R.array.Days)[i];
            try{
                day.put("name",s);
                day.put("leave","08:00 - ");
                day.put("get","16:00");
            }catch(JSONException e){
                e.printStackTrace();
            }
            week.put(day);

        }
        JSONObject mainObj = new JSONObject();
        try{
            mainObj.put("weeknr",31);
            mainObj.put("week", week);
        }catch(JSONException e){
            e.printStackTrace();
        }

            return mainObj;

    }

    @Override
    public void onTimeRangeSelected(int i, int i1, int i2, int i3) {
        Log.d("TIMEPICKER", "CLICK");
    }
}
