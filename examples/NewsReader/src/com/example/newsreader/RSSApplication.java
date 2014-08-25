package com.example.newsreader;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class RSSApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		
		Intent i = new Intent("com.example.newsreader.DOWNLOAD_RSS_XML");
		//Intent intent = new Intent("com.example.newsreader.DOWNLOAD_RSS_XML");
		i.putExtra("URL", "http://www.engadget.com/rss.xml");
		
		PendingIntent pIntent = PendingIntent.getService(getApplicationContext(), 101, i, PendingIntent.FLAG_CANCEL_CURRENT);
		manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 60000, pIntent);
	}

}
