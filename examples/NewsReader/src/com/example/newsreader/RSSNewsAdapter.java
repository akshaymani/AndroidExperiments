package com.example.newsreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RSSNewsAdapter extends BaseAdapter {

	Context context;
	ArrayList<NewsItem> items;

	static public class ViewHolder {
		TextView textView;
	}

	public RSSNewsAdapter(Context context, ArrayList<NewsItem> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		Log.i("RSSNewsAdapter", "RSSFeed Count: " + items.size());
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position; // If using Database then things would be different
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Defining LinearLayout because I don't have access to the inflater object
		LinearLayout mainLayout = null;

		ViewHolder vh = new ViewHolder();
		if (null == convertView) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			mainLayout = (LinearLayout) inflater.inflate(R.layout.row, null);

			vh.textView = (TextView) mainLayout.findViewById(R.id.textView1);

			mainLayout.setTag(vh);

		}
		else {
			mainLayout = (LinearLayout) convertView;
		}

		TextView tv = vh.textView;

		String rssFeed = null;
		Log.i("RSSNewsAdapter", "Position: " + position);
		if (null == rssFeed) {
			rssFeed = items.get(position).toString();
			Log.i("RSSNewsAdapter", "?????: " + rssFeed);
		}
				
		tv.setText(rssFeed);
		
		return mainLayout;
	}


}
