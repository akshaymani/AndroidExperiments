package com.example.myimagesearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class FlickrAdapter extends BaseAdapter {

	Context context;
	ArrayList<FlickrItem> items;
	Executor executor;

	public FlickrAdapter(Context context, ArrayList<FlickrItem> item) {
		super();
		this.items = item;
		this.context = context;

		executor = Executors.newFixedThreadPool(10);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FrameLayout mainLayout = null;
		if (null == convertView) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			mainLayout = (FrameLayout) inflater.inflate(R.layout.cell, null);
		}
		else {
			mainLayout = (FrameLayout) convertView;
		}

		final ImageView iv = (ImageView) mainLayout.findViewById(R.id.imageView1);

		// set position on the image view
		iv.setTag(position);

		final ProgressBar pv = (ProgressBar) mainLayout.findViewById(R.id.progressBar1);

		final FlickrItem item = items.get(position);
		final ImageHandler handler = new ImageHandler(pv, iv, position); 

		// creating runnable to download images in parallel

		// if I post a runnable then that would cause a problem because the views are constantly getting recycled
		// so view 5 might be updated with image at index 1
		// so create a handler and add a msg along with the index of image

		final Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Bitmap image = readBitmap(item.url); 

				if (image == null) {
					image = downloadBitmap(item.url);
					// above line needs to be called in a thread now - create our thread pool and not spawn too many threads
					// ...as on a low end device it might now work smoothly

					writeToFile(item.url, image);
				}

				if (null != image) {
					// is this correct ? No
					/*iv.setImageBitmap(image);
					pv.setVisibility(View.INVISIBLE);*/
					Message msg = Message.obtain();
					Bundle b = new Bundle();
					b.putParcelable("Image", image);
					msg.setData(b);
					handler.sendMessage(msg);
				}
				

			}

			private void writeToFile(String url, Bitmap image) {
				try {
					Uri uri = Uri.parse(url);
					String fileName = uri.getLastPathSegment();
					FileOutputStream outStr = context.openFileOutput(fileName, context.MODE_PRIVATE);

					// For external Storage
					String sdCardState = Environment.getExternalStorageState();
					if (sdCardState.equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
						File sdCardPath = Environment.getExternalStorageDirectory();

						File filePath = new File(sdCardPath,fileName);
						FileOutputStream fout = new FileOutputStream(filePath);
						// WEBP is a very compact form - almost lostless / PNG is also good in terms of compression / JPEG not that good
						boolean success = image.compress(CompressFormat.PNG, 90, outStr);

						if (success) {
							Log.i("FlickrAdapter", "File Cached Successfully");
						}
						else {
							Log.i("FlickrAdapter", "Failed to Cache the File");
						}
					}

					/*// WEBP is a very compact form - almost lostless / PNG is also good in terms of compression / JPEG not that good
					boolean success = image.compress(CompressFormat.PNG, 90, outStr);

					if (success) {
						Log.i("FlickrAdapter", "File Cached Successfully");
					}
					else {
						Log.i("FlickrAdapter", "Failed to Cache the File");
					}*/
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

			private Bitmap readBitmap(String url) {
				Bitmap image;
				try {
					Uri uri = Uri.parse(url);
					String fileName = uri.getLastPathSegment();
					//InputStream inStr = context.openFileInput(fileName);

					// For external Storage
					String sdCardState = Environment.getExternalStorageState();
					if (sdCardState.equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
						File sdCardPath = Environment.getExternalStorageDirectory();
						File filePath = new File(sdCardPath,fileName);

						FileInputStream inStr = new FileInputStream(filePath);
						if (null != inStr) {
							image = BitmapFactory.decodeStream(inStr);
							return image;
						}
					}

					/*if (null != inStr) {
						image = BitmapFactory.decodeStream(inStr);
						return image;
					}*/

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return null;
			}

		};

		executor.execute(runnable);

		return mainLayout;
	}

	public Bitmap downloadBitmap(String link) {
		URL url;
		Bitmap image;
		try {
			url = new URL(link);
			URLConnection connection = url.openConnection();

			InputStream inStr = connection.getInputStream();
			image = BitmapFactory.decodeStream(inStr);
			return image;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}

}
