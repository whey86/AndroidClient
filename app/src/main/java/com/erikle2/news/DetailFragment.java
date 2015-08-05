package com.erikle2.news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikle2.main.R;

/**
 * Created by Erik on 2015-08-05.
 */
public class DetailFragment extends Fragment  {


    private String title, text;

    public static DetailFragment newInstance(News data) {

        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("title", data.getTitle());
        args.putString("text", data.getText());
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
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_news_detail, container, false);
        LinearLayout field = (LinearLayout) v.findViewById(R.id.ll_news_details);
        TextView tvTitle = new TextView(getActivity());
        TextView tvText = new TextView(getActivity());
        tvTitle.setText(title);
        tvText.setText(text);
        field.addView(tvTitle);
        field.addView(tvText);

        Button btn = (Button) v.findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();

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