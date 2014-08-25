package com.example.newsreader;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity implements ListNewsFragmentListener {

	ArrayList<NewsItem> items;
	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// refresh the list view
			Log.i("MainActivity", "Received Updates from the Service");
			
			items = intent.getParcelableArrayListExtra("ITEMS");
			refreshView(items);
		}		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager manager = getFragmentManager();

		ListNewsFragment f = 
				(ListNewsFragment)manager.findFragmentByTag("LNF");

		if (null == f) {
			callListNewsFragment();
		} else {
			f.listener = this;
		}

	}

	private void callListNewsFragment() {
		FragmentManager manager = getFragmentManager();

		ListNewsFragment lf = new ListNewsFragment();
		lf.listener = this;

		FragmentTransaction trans = manager.beginTransaction();

		trans.add(R.id.mainContainer, lf , "LNF");

		trans.commit();
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

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.newsreader.NEW_ITEMS");

		registerReceiver(receiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterReceiver(receiver);
	}

	@Override
	public void refreshView(ArrayList<NewsItem> items) {
		FragmentManager manager = getFragmentManager();
		ListNewsFragment fragment = (ListNewsFragment) manager.findFragmentByTag("LNF");
		
		fragment.refreshView(items);
		
	}

}
