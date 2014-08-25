package com.example.newsreader;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		Intent i = new Intent("com.example.newsreader.DOWNLOAD_RSS_XML");
		//Intent intent = new Intent("com.example.newsreader.DOWNLOAD_RSS_XML");
		intent.putExtra("URL", "http://www.engadget.com/rss.xml");
		
		PendingIntent pIntent = PendingIntent.getService(context, 101, i, PendingIntent.FLAG_CANCEL_CURRENT);
		manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 60000, pIntent);
	}

}
