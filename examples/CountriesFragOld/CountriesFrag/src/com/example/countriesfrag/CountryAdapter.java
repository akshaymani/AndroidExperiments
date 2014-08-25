package com.example.countriesfrag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ActivityManager;

public class CountryAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> countries;
	LruCache<String, Bitmap> mMemoryCache;

	static public class ViewHolder {
		TextView textView;
		ImageView imageView;
	}

	public CountryAdapter(Context context, ArrayList<String> countries) {
		super();
		this.context = context;
		this.countries = countries;

		// initialize the LRU cache
		final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

		final int cacheSize = 1024 * 1024 * memClass/8;

		mMemoryCache = new LruCache<String,Bitmap>(cacheSize) {

			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getByteCount();
			}

		};
	}

	@Override
	public int getCount() {
		return countries.size();
	}

	@Override
	public Object getItem(int position) {
		return countries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position; // If using Database then things would be different
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Defining LinearLayout because I don't have access to the inflater object
		LinearLayout mainLayout = null;

		if (null == convertView) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			mainLayout = (LinearLayout) inflater.inflate(R.layout.row, null);

			ViewHolder vh = new ViewHolder();

			vh.textView = (TextView) mainLayout.findViewById(R.id.textView1);
			vh.imageView = (ImageView) mainLayout.findViewById(R.id.imageView1);

			// attach the view holder to the view &&&&&&
			mainLayout.setTag(vh);

		}
		else {
			mainLayout = (LinearLayout) convertView;
		}

		//LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// inflate row.xml
		//mainLayout = (LinearLayout) inflater.inflate(R.layout.row, null);

		// get access to the text view inside the layout
		// &&&&& (Not needed now and below three lines can be used) TextView tv = (TextView) mainLayout.findViewById(R.id.textView1);

		// get the view holder from the view
		ViewHolder vh = (ViewHolder) mainLayout.getTag();
		TextView tv = vh.textView;
		ImageView iv = vh.imageView;

		// get the country name from the array
		String countryName = (String) getItem(position);

		// display the country name on the text view
		tv.setText(countryName);

		// read the flag image from assets folder
		AssetManager manager = context.getAssets();

		String flagFilePath = "flags-32/"+countryName+".png";

		// open the image file
		Bitmap image = mMemoryCache.get(flagFilePath);
		if (null == image) {
			try {
				InputStream inStr = manager.open(flagFilePath);
				// Instead of us reading the bytes and decode them and then show the bitmap image we use the helper BitMapFactoryClass

				// If the images are heavy, that is not thumbnails then this may take too much time
				// We are inflating the image again and again, so if we can cache the images then the scroll becomes much smoother [LRU cache]
				// In honeycomb then have intrduced the class for creating a cache size

				image = BitmapFactory.decodeStream(inStr); //caching it now
				
				mMemoryCache.put(flagFilePath, image);

				// display the image in the image view
				iv.setImageBitmap(image);

			} catch (IOException e) {
				e.printStackTrace();
				iv.setImageResource(R.drawable.ic_launcher);
			}
		}
		else {
			iv.setImageBitmap(image);
		}
		return mainLayout;
	}

}
