package com.erikle2.parser;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.erikle2.main.MainActivity;
import com.erikle2.news.News;
import com.erikle2.news.NewsFeedAdapter;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 2015-08-03.
 */
public class PostHandler {

    private int mLast = -1;
    private int mPrevious = -1;

    //TODO: What to do with strings? leave it of clean it
    public void getNewPosts(final NewsFeedAdapter adapter) {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.setLimit(2);
        query.orderByDescending("createdAt");
        query.findInBackground( new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<News> news = new ArrayList<News>();
                    for(ParseObject item : list){
                        News n = new News();

                        n.setTitle(item.getString("title"));
                        n.setLocation(item.getString("location"));
                        n.setTime(item.getString("time"));
                        n.setText(item.getString("text"));
                        n.setIndicator((ArrayList<String>) item.get("icons"));
                        n.setType(item.getString("type"));
                        n.setDate(item.getDate("date"));
                        n.setAuthor(item.getString("Author"));
                        n.setCreatedAt(item.getCreatedAt());
                        Log.d("CREATEDAT",item.getCreatedAt().toString());
                        news.add(n);
                    }
                    mLast = list.get(list.size()-1).getInt("number");
                    adapter.addItems(news);
                } else {
                    e.printStackTrace();

                }
            }
        });
    }
    public void getMorePosts(final NewsFeedAdapter adapter){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.setLimit(2);
        if(mLast != -1 && mPrevious != mLast){

            query.orderByDescending("createdAt");
            query.whereLessThan("number", mLast);
             mPrevious =mLast;
        }else{
            Toast.makeText(adapter.getContext(),"No more posts",Toast.LENGTH_SHORT).show();
            return;
        }

        final int count = adapter.getCount();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<News> news = new ArrayList<News>();
                    for (ParseObject item : list) {
                        News n = new News();

                        n.setTitle(item.getString("title"));
                        n.setLocation(item.getString("location"));
                        n.setTime(item.getString("time"));
                        n.setText(item.getString("text"));
                        n.setIndicator((ArrayList<String>) item.get("icons"));
                        n.setType(item.getString("type"));
                        n.setDate(item.getDate("date"));
                        n.setAuthor(item.getString("Author"));
                        n.setCreatedAt(item.getCreatedAt());
                        news.add(n);
                    }

                    adapter.addItems(news);
                } else {
                    e.printStackTrace();

                }
            }
        });
    }
}
