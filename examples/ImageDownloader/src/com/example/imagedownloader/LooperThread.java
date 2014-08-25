package com.example.imagedownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {

	public Handler handler;
	Bitmap image;
	
	@Override
	public void run() {
		super.run();
		
		// create the looper
		Looper.prepare();
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Log.i("Looper Thread", "Inside the Looper Thread");
				Bundle data = msg.getData();
				String url = data.getString("URL");
				downloadImage(url);
				
			}
			
		};
		
		// run the message loop, without the below line thread will come to a halt after run method completes
		Looper.loop();
	}
	
	public void downloadImage(String link) {
		try {
			URL url = new URL(link); 
			// Apache HTTP Clients to communicate with a web server but begining ICS recommended way is using URLConnection class
			// this is done because HTTPClient does a blocking call for the HTTP Request

			URLConnection connection = url.openConnection();

			InputStream inStr = connection.getInputStream();

			image = BitmapFactory.decodeStream(inStr);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
