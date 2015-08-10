package com.erikle2.news;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.erikle2.main.BackHandlerFragment;
import com.erikle2.main.R;
import com.erikle2.network.Connection;
import com.erikle2.parser.PostHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment{


    private ListView newsfeed;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsFragment.
     */

    public static NewsFragment newInstance() {

        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        if (savedInstanceState == null) {
            LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_news, container, false);
            newsfeed = (ListView) v.findViewById(R.id.lv_news);
            final NewsFeedAdapter adapter = new NewsFeedAdapter(getActivity().getApplicationContext(), new ArrayList<News>());
            newsfeed.setAdapter(adapter);

            //Fetch initial news data
            final PostHandler ph = new PostHandler();
            ph.getNewPosts((NewsFeedAdapter) newsfeed.getAdapter());

            //TODO: Check if methods works for big data
            //Button to fetch more posts
            Button more = (Button) v.findViewById(R.id.btn_more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ph.getMorePosts(adapter);
                }
            });
            newsfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();

                    Fragment f = DetailFragment.newInstance((News) newsfeed.getAdapter().getItem(position));
                    FragmentTransaction ft = fm.beginTransaction()
                            .replace(R.id.container, f);

                    ft.commit();
                }
            });
            return v;
//        }else{
//            return ;
//        }

    }
}
