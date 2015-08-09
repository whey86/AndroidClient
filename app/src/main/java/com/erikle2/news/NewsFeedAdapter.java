package com.erikle2.news;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
    public NewsFeedAdapter(Context context, ArrayList<News> data) {
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

        if(news.getType().equals(news.POST_TYPE_EVENT)) {
            //Set icons
            ArrayList<String> icons = news.getIndicator();

            LinearLayout iconContainer = (LinearLayout) rowView.findViewById(R.id.icon_container);
            for (int i = 0; i < icons.size(); i++) {
                ImageView img = new ImageView(context);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                llp.setMargins(7, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
                img.setImageResource(getImgResourceByName(icons.get(i)));
                img.setLayoutParams(llp);
                iconContainer.addView(img);
            }
            LinearLayout infoContainer = (LinearLayout) rowView.findViewById(R.id.shortinfo_container);

            // if location exist, add it to the view
            if (!news.getLocation().equals( "")) {
                LinearLayout ll = new LinearLayout(context);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                ImageView iv = new ImageView(context);
                iv.setImageResource(R.drawable.ic_location_on_black_24dp);

                ll.addView(iv);
                TextView tvLocation = new TextView(context);
                tvLocation.setText(news.getLocation());
                tvLocation.setTextColor(Color.BLACK);

                ll.addView(tvLocation);
                infoContainer.addView(ll);
            }
//     // if date exist, add it to the view
            if (news.getDate() != null) {
                LinearLayout ll = new LinearLayout(context);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                ImageView iv = new ImageView(context);
                iv.setImageResource(R.drawable.ic_event_note_black_24dp);

                ll.addView(iv);
                TextView tvDate = new TextView(context);
                tvDate.setText(news.getDate().toString().substring(0, 16));
                tvDate.setTextColor(Color.BLACK);

                ll.addView(tvDate);
                infoContainer.addView(ll);
            }
        }
            LinearLayout main = (LinearLayout) rowView.findViewById(R.id.ll_news_main);
            // if time exist, add it to the view
            if (!news.getText().equals("")) {
                TextView tvText = new TextView(context);
                String text = news.getText();
                if (text.length() > 150) {
                    text = text.substring(0, 147) + "...";
                }

                tvText.setText(text);


                main.addView(tvText);
            }

        // if type exist, aset the type color
//        if ( news.getType() != "") {
//            LinearLayout header =(LinearLayout)rowView.findViewById(R.id.header);
//            String type = news.getType();
//            Resources res =  getContext().getResources();
//            if(type.equals("event")){
//                header.setBackgroundColor(res.getColor(R.color.primary));
//            }else if(type.equals("info")){
//                header.setBackgroundColor(res.getColor(R.color.primary));
//            }
//        }
        return rowView;

//        }else{
//            return super.getView(position, convertView, parent);
//        }

    }

    public static int getImgResourceByName(String posistion) {

        switch (posistion) {
            case "0":
                return R.drawable.food;
            case "1":
                return R.drawable.boot;
            case "2":
                return R.drawable.clock;
        }
        return 0;
    }
    public void addItems(ArrayList<News> list){
        addAll(list);
        notifyDataSetChanged();
    }

}
