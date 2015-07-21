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

/**
 * Created by Erik on 2015-07-21.
 */
public class TimesFragment extends android.support.v4.app.Fragment {

    Button btnPrevious;
    Button btnNext;
    ListView listViewTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //If no saved instance create new view
        if(savedInstanceState == null){



            LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_times,container,false);

            btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
            btnNext = (Button)v.findViewById(R.id.btnNext);

            listViewTimes = (ListView) v.findViewById(R.id.lv_times);
            String [] s = new String[]{"Måndag","Tisdag","Onsdag"};
            TimeListAdapter adapter = new TimeListAdapter(getActivity().getApplicationContext(),s);
            listViewTimes.setAdapter(adapter);

            listViewTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("Item click",""+ position);
                    Toast.makeText(getActivity().getApplicationContext()," " + position,Toast.LENGTH_SHORT).show();
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
}
