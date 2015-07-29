package com.erikle2.news;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikle2.main.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 *  NewsFeedAdapter handles the views to be shown in the newsfeed
 */
public class NewsFeedAdapter extends ArrayAdapter<String> {
    /**
     * Ref. to parent context
     */
    private Context context;
    /**
     * JSONobj holding the newsfeed data
     */
    private JSONObject data;
    /**
     * @param context
     * @param data
     */
    public NewsFeedAdapter(Context context, JSONObject data) {
        super(context, R.layout.list_layout_news,context.getResources().getStringArray(R.array.Days));
        this.context = context;
        this.data = data;
    }

    /**
     * Returns the view for the row
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if no exisiting view, create a new one
        if(convertView != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.list_layout_news, parent, false);
            String title = "";
            String type;

            try{
                JSONObject obj = (JSONObject)data.getJSONArray("newsfeed").get(position);
                title = obj.getString("title");
            }catch(JSONException e){
                e.printStackTrace();
            }

            TextView tvTitle = (TextView) rowView.findViewById(R.id.tv_title);
            tvTitle.setText(title);

        ImageView img = (ImageView) rowView.findViewById(R.id.imageView);
        img.setImageResource(R.drawable.ic_drawer);
            return rowView;

        }else{
            return super.getView(position, convertView, parent);
        }

    }
}
