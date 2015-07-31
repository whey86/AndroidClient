package com.erikle2.news;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.erikle2.main.R;
import com.erikle2.network.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ListView newsfeed;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FrameLayout v = (FrameLayout) inflater.inflate(R.layout.fragment_news, container, false);
        newsfeed = (ListView) v.findViewById(R.id.lv_news);
        NewsFeedAdapter adapter = new NewsFeedAdapter(getActivity().getApplicationContext(), createNewsArray());
        newsfeed.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
//        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    private News[] createNewsArray() {
        News[] mNews = null;
        JSONObject newsData = new Connection().getNews();

        try {
            JSONArray jo = newsData.getJSONArray("newsfeed");
            mNews = new News[jo.length()];
            for (int i = 0; i < jo.length(); i++) {
                News newsObj = new News();
                JSONObject jNews = (JSONObject)jo.get(i);
                newsObj.setTitle((String) jNews.get("title"));
                newsObj.setLocation((String) jNews.get("location"));
                newsObj.setType((String) jNews.get("type"));
//                newsObj.setText((String) jNews.get("text"));
//                newsObj.setTime((String) jNews.get("time"));
                JSONArray iJa = jNews.getJSONArray("icons");
                int [] array = new int[iJa.length()];
                for(int j = 0; j<iJa.length(); j++){
                    array[j] =(int) iJa.get(j);
                }
                newsObj.setIndicator(array);
                mNews[i] = newsObj;
                Log.d("test", "test");
            }

        } catch (JSONException e) {
            Log.d("Parsing exception","stop");
                e.printStackTrace();
        } finally {


        }
        Log.d("JSON", "news not returned");
        return mNews;
    }


}
