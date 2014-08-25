package com.example.imagedownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


// Input param / Progress / Result
public class ImageDownloaderTask extends AsyncTask<String, Void, Boolean> {

	ImageView imageView;
	Context context;
	Bitmap image;
	ProgressDialog dialog;
	
	public ImageDownloaderTask(ImageView iv,Context ctx) {
		this.imageView = iv;
		this.context = ctx;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		downloadImage(params[0]);
		return true;
	}
	
	@Override
	protected void onPreExecute() { // Show the progress and kill it in onPostExecute Method
		super.onPreExecute();
		if (null == dialog) {
			dialog = new ProgressDialog(context);
		}
		dialog.setMessage("Downloading Image...");
		dialog.show();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (null != image) {
			imageView.setImageBitmap(image);
		}
		
		if (null != dialog) {
			dialog.hide();
		}
	}

	public void downloadImage(String link) {
		try {
			URL url = new URL(link); 
			// Apache HTTP Clients to communicate with a web server but begining ICS recommended way is using URLConnection class

			URLConnection connection = url.openConnection();

			InputStream inStr = connection.getInputStream();

			image = BitmapFactory.decodeStream(inStr);
			
			if (null != image) {
				Log.i("ImageDownloaderTask", "Downloaded Image");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
