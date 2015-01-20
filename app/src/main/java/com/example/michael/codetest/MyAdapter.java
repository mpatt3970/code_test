package com.example.michael.codetest;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by michael on 1/8/15.
 */
public class MyAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private int mLayoutId;
    private Item[] mItems;

    public MyAdapter(Context context, int layoutId, Item[] items) {
        super(context, layoutId, items);
        mContext = context;
        mLayoutId = layoutId;
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < getCount()) {
            View row = convertView;
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutId, parent, false);
            TextView titleView = (TextView) row.findViewById(R.id.title);
            titleView.setText(mItems[position].getTitle());
            String imageUrl = mItems[position].getImage();
            ImageView imageView = (ImageView) row.findViewById(R.id.image);
            if (imageUrl != null && imageUrl.length() > 0) {
                Picasso.with(mContext).load(Uri.parse(imageUrl)).into(imageView);
            } else {
                ((RelativeLayout) row).removeView(imageView);
            }
            String author = mItems[position].getAuthor();
            if (author != null && author.length() > 0) {
                TextView authorView = (TextView) row.findViewById(R.id.author);
                authorView.setText("By  " + author);
            }
            return row;
        }
        return null;
    }


}
