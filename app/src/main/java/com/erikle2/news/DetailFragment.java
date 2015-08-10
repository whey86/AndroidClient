package com.erikle2.news;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikle2.main.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 2015-08-05.
 */
public class DetailFragment extends Fragment {
    private String type, location, time, title, text, author;
    private String date, createdAt;
    private ArrayList<String> notifications;
//    private static  final String [] ICON_LABELS = new String[]{R.array.Notifications[0]," Regnkläder"," Deadline"};



    public static DetailFragment newInstance(News data) {

        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();

        args.putString("title", data.getTitle());
        args.putString("text", data.getText());
        args.putString("type", data.getType());
        args.putString("location", data.getLocation());
        args.putString("author", data.getAuthor());
        args.putString("createdAt", data.getCreatedAt().toString().substring(0, 16));
        args.putString("date", data.getDate().toString().substring(0, 16));
        args.putStringArrayList("icons",data.getIndicator());
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            text = getArguments().getString("text");
            author = getArguments().getString("author");
            location = getArguments().getString("location");
            type = getArguments().getString("type");
            date = getArguments().getString("date");
            createdAt = getArguments().getString("createdAt");
            notifications = getArguments().getStringArrayList("icons");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_news_detail, container, false);
        LinearLayout field = (LinearLayout) v.findViewById(R.id.ll_news_details);
        TextView tvTitle = new TextView(getActivity());
        TextView tvText = new TextView(getActivity());
        tvTitle.setText(title);
        tvTitle.setTextSize(30);

        tvText.setText(text);
        field.addView(tvTitle);

        if (type.equals("event")) {

            LinearLayout eventInfo = (LinearLayout)field.findViewById(R.id.ll_event_info);
            // if location exist, add it to the view
            if (location != "") {
                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.HORIZONTAL);

                ImageView iv = new ImageView(getActivity());
                iv.setImageResource(R.drawable.ic_location_on_black_24dp);

                ll.addView(iv);
                TextView tvLocation = new TextView(getActivity());
                tvLocation.setText(location);
                tvLocation.setTextColor(Color.BLACK);

                ll.addView(tvLocation);
                eventInfo.addView(ll);
            }
//     // if date exist, add it to the view
            if (date != null) {
                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.HORIZONTAL);

                ImageView iv = new ImageView(getActivity());
                iv.setImageResource(R.drawable.ic_event_note_black_24dp);

                ll.addView(iv);
                TextView tvDate = new TextView(getActivity());
                tvDate.setText(date);
                tvDate.setTextColor(Color.BLACK);

                ll.addView(tvDate);
                eventInfo.addView(ll);
            }



//            Add notification icons
            LinearLayout eventNotifications = (LinearLayout) field.findViewById(R.id.ll_event_notification);
            for (String icon : notifications) {
                LinearLayout icon_con = new LinearLayout(getActivity());
                icon_con.setOrientation(LinearLayout.HORIZONTAL);
                ImageView img = new ImageView(getActivity());
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                llp.setMargins(7, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
                llp.width = 35;
                llp.height = 35;
                img.setImageResource(NewsFeedAdapter.getImgResourceByName(icon));
                img.setLayoutParams(llp);
                icon_con.addView(img);

                TextView label = new TextView(getActivity());
                label.setText(getActivity().getResources().getStringArray(R.array.Notifications)[Integer.parseInt(icon)]);
                icon_con.addView(label);
                eventNotifications.addView(icon_con);
            }
        }
        field.addView(tvText);

        //Container for author and createdAt
        LinearLayout ll_bottom = new LinearLayout(getActivity());
        ll_bottom.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        lp.setMargins(0, 10, 0, 10);
        ll_bottom.setLayoutParams(lp);
        TextView tvAuthor = new TextView(getActivity());
        TextView tvCreatedAt = new TextView(getActivity());
        tvAuthor.setText(author);
        tvCreatedAt.setText("Reviderad " + createdAt +"\n");

        ll_bottom.addView(tvCreatedAt);
        ll_bottom.addView(tvAuthor);


        field.addView(ll_bottom);


        Button btn = (Button) v.findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();

                Fragment f = NewsFragment.newInstance();
                FragmentTransaction ft = fm.beginTransaction()
                        .replace(R.id.container, f);
                ft.commit();
            }
        });
        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

}