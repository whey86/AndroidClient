package com.erikle2.news;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

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
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.fragment_news, container, false);
        newsfeed = (ListView) v.findViewById(R.id.lv_news);
        final NewsFeedAdapter adapter = new NewsFeedAdapter(getActivity().getApplicationContext(), new ArrayList<News>());
        newsfeed.setAdapter(adapter);

       final PostHandler ph = new PostHandler();
        ph.getNewPosts((NewsFeedAdapter) newsfeed.getAdapter());

        Button more = (Button)v.findViewById(R.id.btn_more);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph.getMorePosts(adapter);
            }
        });

        newsfeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm =  getActivity().getFragmentManager();

                Fragment f = DetailFragment.newInstance((News)newsfeed.getAdapter().getItem(position));
                FragmentTransaction ft = fm.beginTransaction()
                        .replace(R.id.container, f);

                ft.commit();
            }
        });



        return v;
//        return inflater.inflate(R.layout.fragment_news, container, false);
    }
//
//    private News[] createNewsArray() {
//        News[] mNews = null;
//        JSONObject newsData = new Connection().getNews();
//
//        try {
//            JSONArray jo = newsData.getJSONArray("newsfeed");
//            mNews = new News[jo.length()];
//            for (int i = 0; i < jo.length(); i++) {
//                News newsObj = new News();
//                JSONObject jNews = (JSONObject)jo.get(i);
//                newsObj.setTitle((String) jNews.get("title"));
//                newsObj.setLocation((String) jNews.get("location"));
//                newsObj.setType((String) jNews.get("type"));
////                newsObj.setText((String) jNews.get("text"));
////                newsObj.setTime((String) jNews.get("time"));
//                JSONArray iJa = jNews.getJSONArray("icons");
//                int [] array = new int[iJa.length()];
//                for(int j = 0; j<iJa.length(); j++){
//                    array[j] =(int) iJa.get(j);
//                }
//                newsObj.setIndicator(array);
//                mNews[i] = newsObj;
//                Log.d("test", "test");
//            }
//
//        } catch (JSONException e) {
//            Log.d("Parsing exception","stop");
//                e.printStackTrace();
//        } finally {
//
//
//        }
//        Log.d("JSON", "news not returned");
//        return mNews;
//    }


}
