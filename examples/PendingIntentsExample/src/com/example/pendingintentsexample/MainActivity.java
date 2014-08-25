package com.example.pendingintentsexample;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void setAlarm(View view) {
		// get access to alarm managers
		AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		
		// create an intent for app component
		Intent intent = new Intent(this, MainActivity.class);
		
		// wrap the intent inside a pending intent
		PendingIntent pIntent = PendingIntent.getActivity(this, // context
				101, // identifier for pending intent
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		manager.set(AlarmManager.RTC_WAKEUP, 60000, pIntent);
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
