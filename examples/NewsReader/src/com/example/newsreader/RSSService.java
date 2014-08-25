package com.example.newsreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

public class RSSService extends IntentService {

	RSSHandler rssHandler = new RSSHandler();
	
	public RSSService() {
		super(RSSService.class.getName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String link = intent.getStringExtra("URL");
		Log.i("Intent Value", link);
		downloadAndParseXML(link);
	}
	
	public void downloadAndParseXML(String link) {
		try {
			URL url = new URL(link);
			URLConnection connection = url.openConnection();
			InputStream inStr = connection.getInputStream();
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(inStr, rssHandler);
			
			Intent intent = new Intent("com.example.newsreader.NEW_ITEMS");
			intent.putExtra("ITEMS", rssHandler.items);
			
			// send a broadcast
			getApplicationContext().sendBroadcast(intent);
			
			sendNotification(getApplicationContext());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// aidl file -> java interface for remote service we have to create so that android system can recognise it
	// Alarm Manager / Location Manager / Notification Manager - Remote Services which are exposing some interfaces and bind to them
	// Launches a new activity 
	// Clear the notification in the main activity by getting hold of notification manager and clearing the message
	private void sendNotification(Context context) {
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent intent = new Intent(context,MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 102, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Notification notif = new Notification(R.drawable.ic_launcher,
												"New Items",
												System.currentTimeMillis());
		
		notif.setLatestEventInfo(context, "NewsReader", "New Items have arrived", pIntent);
		
		manager.notify(1001, notif);
	}
	
}
