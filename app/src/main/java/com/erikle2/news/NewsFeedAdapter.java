package com.erikle2.news;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikle2.main.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * NewsFeedAdapter handles the views to be shown in the newsfeed
 */
public class NewsFeedAdapter extends ArrayAdapter<News> {
    /**
     * Ref. to parent context
     */
    private Context context;
    /**
     * JSONobj holding the newsfeed data
     */

    private int[] imgViews = new int[]{};


    /**
     * @param context
     * @param data
     */
    public NewsFeedAdapter(Context context, News[] data) {
        super(context, R.layout.list_layout_news, data);
        this.context = context;

    }

    /**
     * Returns the view for the row
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if no exisiting view, create a new one
//        if(convertView == null){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_layout_news, parent, false);

        //Set title
        News news = getItem(position);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tv_title);
        tvTitle.setText(news.getTitle());

        //Set icons
        int[] icons = news.getIndicator();
        LinearLayout iconContainer = (LinearLayout) rowView.findViewById(R.id.icon_container);
        for (int i = 0; i < icons.length; i++) {
            ImageView img = new ImageView(context);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(7, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
            img.setImageResource(getImgResourceByPosition(icons[i]));
            img.setLayoutParams(llp);
            iconContainer.addView(img);
        }
        LinearLayout infoContainer = (LinearLayout) rowView.findViewById(R.id.shortinfo_container);

        // if location exist, add it to the view
        if (news.getLocation() != "") {
            TextView tvLocation = new TextView(context);
            tvLocation.setText(news.getLocation());
            infoContainer.addView(tvLocation);
        }

        // if time exist, add it to the view
        if (news.getTime() != "") {
            TextView tvTime = new TextView(context);
            tvTime.setText(news.getTime());
            infoContainer.addView(tvTime);
        }
        //Trying to
        // retrieve main obj
//        try {
////            JSONArray   mainObj =  data.getJSONArray("newsfeed");
////            JSONObject  obj = mainObj.getJSONObject(position);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e("JSONException", "Couldnt retrieve main JSON obj");
//
//        }
//        finally {
//            //Trying to retrieve title
//            try {
//                title = mainObj.getString("title");
//                TextView tvTitle = (TextView) rowView.findViewById(R.id.tv_title);
//                tvTitle.setText(title);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.e("JSONException", "Couldnt retrieve main JSON obj");
//            }
//
//            LinearLayout iconContainer = (LinearLayout) rowView.findViewById(R.id.icon_container);
//            //Trying to retrieve icons
//            try {
//                icons = mainObj.getJSONArray("icons");
//
//                //        //Set the indicatior icons in header
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.e("JSONException", "Couldnt retrieve main JSON obj");
//            }
//
//        }


        //


//        TextView tvText = (TextView) rowView.findViewById(R.id.tv_text);
//        tvText.setText(text);

//

//        text = obj.getString("text");
//        type = obj.getString("type");

//


        return rowView;

//        }else{
//            return super.getView(position, convertView, parent);
//        }

    }

    private int getImgResourceByPosition(int posistion) {

        switch (posistion) {
            case 0:
                return R.drawable.food;
            case 1:
                return R.drawable.boot;
            case 2:
                return R.drawable.clock;
        }
        return 0;
    }
}
