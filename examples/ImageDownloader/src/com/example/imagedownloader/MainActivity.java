package com.example.imagedownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	Bitmap image;
	ImageView imageView;
	
	/*Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			imageView.setImageBitmap(image);
		}
		
	};*/
	
	//ImageHandler handler;
	Handler handler = new Handler();

	//Multiple threads can use the same handler
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		//handler = new ImageHandler(imageView);
	}

	// Simulating a blocking call
	public void download(View view) {
		/*try {
			Thread.sleep(10000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}	*/	

		// Now NetworkOnMainThreadException will come without creating a background thread beginning ICS

		Runnable r = new Runnable() {

			@Override
			public void run() {
				// By default storing the image in memory and not on the file system
				downloadImage("http://www.androidcentral.com/sites/androidcentral.com/files/styles/w345h196crop/public/postimages/684/podcast_featured_63.jpg?itok=nmWxDpht");
			}
		};
		
		Thread th = new Thread(r);
		th.start();

	}

	public void downloadImage(String link) {
		try {
			URL url = new URL(link); 
			// Apache HTTP Clients to communicate with a web server but begining ICS recommended way is using URLConnection class
			// this is done because HTTPClient does a blocking call for the HTTP Request

			URLConnection connection = url.openConnection();

			InputStream inStr = connection.getInputStream();

			image = BitmapFactory.decodeStream(inStr);
			if (null != image) {
				Log.i("MainActivity", "Downloaded Bitmap");
				// Called from WrongThreadException will come so will write the below line in Handler
				//imageView.setImageBitmap(image);
				
				// Send a message to the main thread handler
				
				// Way 1 - Non static handler
				/*Message msg = Message.obtain(handler); // To recycle objects instead of doing Message msg = new Message()
				handler.sendMessage(msg);*/
				
				// Way 2 - ImageHandler - Static Handler cannot update member variables
				/*Message msg = Message.obtain();
				Bundle bundle = new Bundle();
				bundle.putParcelable("IMAGE", image);
				msg.setData(bundle);
				
				handler.sendMessage(msg);*/
				
				Runnable r = new Runnable() {

					@Override
					public void run() {
						imageView.setImageBitmap(image);
					}
					
				};
				handler.post(r);
			}
			else {
				Log.i("MainActivity", "Unable to Download Bitmap");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
