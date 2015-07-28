package com.example.erik.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erik on 2015-07-21.
 */
public class TimesFragment extends android.support.v4.app.Fragment {

    Button btnPrevious;
    Button btnNext;
    ListView listViewTimes;

    private int lastClick = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //If no saved instance create new view
        if(savedInstanceState == null){



            LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_times,container,false);

//            btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
//            btnNext = (Button)v.findViewById(R.id.btnNext);

            listViewTimes = (ListView) v.findViewById(R.id.lv_times);

            TimeListAdapter adapter = new TimeListAdapter(getActivity().getApplicationContext(), getActivity().getFragmentManager(),getTimes());
            listViewTimes.setAdapter(adapter);

            //ListItem is clicked
            listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int preClick = lastClick;
                    lastClick =position;

                    if(preClick != -1){
                        Log.e("Previous click", "" + preClick);
                        View preView =  parent.getAdapter().getView(preClick,null,parent);
                        View preToolbar = preView.findViewById(R.id.toolbar);

                        // Creating the expand animation for the item
                        ExpandAnimation   preExpand = new ExpandAnimation(preToolbar, 500);

                        // Start the animation on the toolbar
                        preToolbar.startAnimation(preExpand);
                    }


                    View toolbar = view.findViewById(R.id.toolbar);

                        // Creating the expand animation for the item
                    ExpandAnimation   curExpand = new ExpandAnimation(toolbar, 500);

                    // Start the animation on the toolbar
                    toolbar.startAnimation(curExpand);
//
                    return;


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
            mainObj.put("week", week);
        }catch(JSONException e){
            e.printStackTrace();
        }

            return mainObj;

    }
}
