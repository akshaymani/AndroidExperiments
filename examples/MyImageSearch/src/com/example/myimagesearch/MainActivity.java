package com.example.myimagesearch;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends ActionBarActivity {

	GridView imageGridView;
	ArrayList<FlickrItem> items = new ArrayList<FlickrItem>();
	FlickrAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageGridView = (GridView) findViewById(R.id.gridView1);
		adapter = new FlickrAdapter(this, items);
		imageGridView.setAdapter(adapter);
	}
	
	// To avoid a memory leak on configuration change making it a inner class
	class FlickrDownloader extends AsyncTask<Void, Void, Void> {

		
		
		@Override
		protected Void doInBackground(Void... params) {
			FlickrGetter getter = new FlickrGetter();
			
			ArrayList<FlickrItem> newItems = getter.fetchItems();
			
			// clear the existing array
			items.clear();
			
			// add the new items to the array
			items.addAll(newItems);
			
			// is this correct ? - Wrong rebuilding the list view and should not be done in background
			//adapter.notifyDataSetChanged();
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			adapter.notifyDataSetChanged();
		}
		
	}
	
	public void search(View view) {
		// get the flickr data
		FlickrDownloader downloader = new FlickrDownloader();
		downloader.execute();
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
